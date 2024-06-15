package org.ivkoandrv.tntmoddetector.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.TntBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * This class detects when players set TNT blocks on fire and notifies the admins
 * It uses mixins to inject code into the TNT block's ignition method
 */

@Mixin(TntBlock.class)
public class DynamiteDetector {

    /**
     *
     * Injects code to detect when a TNT block is ignited by player
     *
     * @param state     The current state of the TNT block
     * @param world     The world in which the TNT block exists
     * @param pos       The position of the TNT block in the world
     * @param player    The player who ignited the TNT block
     * @param hand      The player's hand holding the igniting item (Flint or Fire charge)
     * @param hit       The hit result indicating where the block was clicked
     * @param cir       Callback info provided by the mixin
     */
    @Inject(at = @At("HEAD"), method = "onUse")

    private void onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit, CallbackInfoReturnable<ActionResult> cir) {
        if (!world.isClient) {
            ItemStack itemStack = player.getStackInHand(hand);
            if (itemStack.getItem() == Items.FLINT_AND_STEEL || itemStack.getItem() == Items.FIRE_CHARGE) {
                notifyAdmins((ServerPlayerEntity) player, pos);
            }
        }
    }

    /**
     *
     * Private function which send notification to the all admins in game
     *
     * @param player    The player who ignited the TNT
     * @param pos       The position of the ignited block
     */
    private void notifyAdmins(ServerPlayerEntity player, BlockPos pos) {
        Text message = Text.literal("Player " + player.getName().getString() + " ignited TNT at " + pos.getX() + ", " + pos.getY() + ", " + pos.getZ());

        MinecraftServer server = player.getServer();
        if (server != null) {
            for (ServerPlayerEntity serverPlayer : server.getPlayerManager().getPlayerList()) {
                if (serverPlayer.hasPermissionLevel(2)) {
                    serverPlayer.sendMessage(message, false);
                }
            }
        }
    }
}

