package alexanders.mods.lop.gen

import alexanders.mods.lop.LOP
import alexanders.mods.lop.init.Tiles
import de.ellpeck.rockbottom.api.RockBottomAPI
import de.ellpeck.rockbottom.api.tile.state.TileState
import de.ellpeck.rockbottom.api.world.gen.IWorldGenerator
import de.ellpeck.rockbottom.api.world.gen.WorldGenOre

class PearlOreGen : WorldGenOre(), IWorldGenerator {
    override fun getMaxAmount(): Int = 4

    override fun getOreState(): TileState = Tiles.pearlOre.defState

    override fun getHighestGridPos(): Int = -2

    override fun getLowestGridPos(): Int = -3

    override fun getClusterRadiusY(): Int = 6

    override fun getClusterRadiusX(): Int = 6

    override fun getPriority(): Int = 210

    companion object {
        fun register() {
            RockBottomAPI.WORLD_GENERATORS.register(RockBottomAPI.createRes(LOP.instance, "pearl_ore_gen"),PearlOreGen::class.java)
        }
    }
}
