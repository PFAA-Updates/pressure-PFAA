/*
 * Copyright (c) bdew, 2013 - 2014
 * https://github.com/bdew/pressure
 *
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license located in
 * http://bdew.net/minecraft-mod-public-license/
 */

package net.bdew.pressure.blocks.tank

import net.bdew.lib.block.NamedBlock
import net.bdew.lib.multiblock.block.BlockController
import net.bdew.lib.multiblock.tile.TileController
import net.bdew.pressure.PressureResourceProvider
import net.minecraft.block.material.Material

class BaseController[T <: TileController](name: String, TEClass: Class[T])
  extends BlockController(name, Material.iron, TEClass) with NamedBlock {
  override def resources = PressureResourceProvider

  setHardness(1)
}
