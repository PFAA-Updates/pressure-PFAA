/*
 * Copyright (c) bdew, 2013 - 2015
 * https://github.com/bdew/pressure
 *
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license located in
 * http://bdew.net/minecraft-mod-public-license/
 */

package net.bdew.pressure.pressurenet

import net.bdew.lib.Misc
import net.bdew.lib.PimpVanilla._
import net.bdew.pressure.api.{IFilterable, IFilterableProvider, IPressureConnectableBlock, IPressureExtension}
import net.minecraft.block.Block
import net.minecraft.entity.player.EntityPlayerMP
import net.minecraft.item.ItemStack
import net.minecraft.util.{BlockPos, EnumFacing}
import net.minecraft.world.{IBlockAccess, World}

object InternalPressureExtension extends IPressureExtension with IFilterableProvider {
  override def canPipeConnectTo(w: IBlockAccess, pos: BlockPos, side: EnumFacing) =
    w.getBlockSafe[IPressureConnectableBlock](pos) exists {
      _.canConnectTo(w, pos, side)
    }

  override def canPipeConnectFrom(w: IBlockAccess, pos: BlockPos, side: EnumFacing) = isConnectableBlock(w, pos)

  override def isConnectableBlock(w: IBlockAccess, pos: BlockPos) =
    w.getBlockSafe[IPressureConnectableBlock](pos).isDefined

  override def isTraversableBlock(world: IBlockAccess, pos: BlockPos) =
    world.getBlockSafe[IPressureConnectableBlock](pos) exists {
      _.isTraversable(world, pos)
    }

  override def tryPlaceBlock(w: World, pos: BlockPos, b: Block, p: EntityPlayerMP) = {
    if (w.isAirBlock(pos) || w.getBlockState(pos).getBlock.isReplaceable(w, pos)) {
      val st = b.onBlockPlaced(w, pos, EnumFacing.UP, 0, 0, 0, 0, p)
      if (w.setBlockState(pos, st, 3)) {
        b.onBlockPlacedBy(w, pos, st, p, new ItemStack(b))
        true
      } else false
    } else false
  }

  override def getFilterableForWorldCoordinates(world: World, pos: BlockPos, side: EnumFacing) = {
    (Option(world.getTileEntity(pos)) flatMap Misc.asInstanceOpt(classOf[IFilterable])).orNull
  }
}
