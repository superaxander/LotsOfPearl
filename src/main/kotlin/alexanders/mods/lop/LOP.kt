package alexanders.mods.lop

import alexanders.mods.lop.event.ItemUsageListener
import alexanders.mods.lop.gen.PearlOreGen
import alexanders.mods.lop.gen.SlimePoolGen
import alexanders.mods.lop.init.*
import alexanders.mods.lop.render.ConfigGUI
import de.ellpeck.rockbottom.api.IApiHandler
import de.ellpeck.rockbottom.api.IGameInstance
import de.ellpeck.rockbottom.api.assets.IAssetManager
import de.ellpeck.rockbottom.api.event.IEventHandler
import de.ellpeck.rockbottom.api.event.impl.EntityTickEvent
import de.ellpeck.rockbottom.api.gui.Gui
import de.ellpeck.rockbottom.api.mod.IMod


class LOP() : IMod {

    companion object {
        lateinit var instance: LOP
    }

    init {
        instance = this
    }

    val configManager = ConfigurationManager()

    override fun getVersion() = "0.7"

    override fun getId() = "lop"

    override fun getDisplayName() = "Lots of Pearls"

    override fun getResourceLocation() = "/assets/$id"

    override fun getDescription(): String = "Adds a bunch of pearls. What else did you expect?"

    override fun getModGuiClass(): Class<out Gui> {
        return ConfigGUI::class.java
    }

    override fun init(game: IGameInstance, assetManager: IAssetManager, apiHandler: IApiHandler, eventHandler: IEventHandler) {
        Resources.init()
        
        val itemUsageListener = ItemUsageListener()
        // Register event handlers
        eventHandler.registerListener(EntityTickEvent::class.java, itemUsageListener)
        game.container.input.addMouseListener(itemUsageListener)

        // Register network packets
        Packets.init()

        // Register items
        Items.init()

        // Register tiles
        Tiles.init()

        // Register entities
        Entities.init()
        
        PearlOreGen().register()
        SlimePoolGen().register()
        
        Recipes.init()
    }
}
