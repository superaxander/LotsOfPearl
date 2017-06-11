package alexanders.mods.lop

import alexanders.mods.lop.entity.BouncyPearlEntity
import alexanders.mods.lop.entity.PearlEntity
import alexanders.mods.lop.entity.RideablePearlEntity
import alexanders.mods.lop.event.ItemUsageListener
import alexanders.mods.lop.item.BouncyPearlItem
import alexanders.mods.lop.item.PearlItem
import alexanders.mods.lop.item.RideablePearlItem
import alexanders.mods.lop.net.CooldownUpdatePacket
import alexanders.mods.lop.net.EntityPositionUpdatePacket
import alexanders.mods.lop.net.ItemUsePacket
import de.ellpeck.rockbottom.api.IApiHandler
import de.ellpeck.rockbottom.api.IGameInstance
import de.ellpeck.rockbottom.api.RockBottomAPI
import de.ellpeck.rockbottom.api.assets.IAssetManager
import de.ellpeck.rockbottom.api.event.IEventHandler
import de.ellpeck.rockbottom.api.event.impl.EntityTickEvent
import de.ellpeck.rockbottom.api.mod.IMod


class LOP() : IMod {

    companion object {
        lateinit var instance: LOP
    }

    init {
        instance = this
    }

    val PEARL_RESOURCE = RockBottomAPI.createRes(this, "pearl")
    val BOUNCY_PEARL_RESOURCE = RockBottomAPI.createRes(this, "bouncy_pearl")
    val RIDEABLE_PEARL_RESOURCE = RockBottomAPI.createRes(this, "rideable_pearl")
    val COOLDOWN_RESOURCE = RockBottomAPI.createRes(this, "cooldown")
    val PEARL_DESC_RESOURCE = RockBottomAPI.createRes(this, "desc.pearl")
    val BOUNCY_PEARL_DESC_RESOURCE = RockBottomAPI.createRes(this, "desc.bouncy_pearl")
    val RIDEABLE_PEARL_DESC_RESOURCE = RockBottomAPI.createRes(this, "desc.rideable_pearl")
    val TELEPORTATION_PARTICLE_RESOURCE = RockBottomAPI.createRes(this, "particles.teleportation")

    override fun getVersion() = "0.3"

    override fun getId() = "lop"

    override fun getDisplayName() = "Lots of Pearls"

    override fun getResourceLocation() = "/assets/$id"

    override fun getDescription(): String = "Adds a bunch of pearls. What else did you expect?"

    override fun init(game: IGameInstance, assetManager: IAssetManager, apiHandler: IApiHandler, eventHandler: IEventHandler) {
        val itemUsageListener = ItemUsageListener()
        // Register event handlers
        eventHandler.registerListener(EntityTickEvent::class.java, itemUsageListener)
        game.container.input.addMouseListener(itemUsageListener)

        // Register network packets
        RockBottomAPI.PACKET_REGISTRY.register(RockBottomAPI.PACKET_REGISTRY.nextFreeId, ItemUsePacket::class.java)
        RockBottomAPI.PACKET_REGISTRY.register(RockBottomAPI.PACKET_REGISTRY.nextFreeId, EntityPositionUpdatePacket::class.java)
        RockBottomAPI.PACKET_REGISTRY.register(RockBottomAPI.PACKET_REGISTRY.nextFreeId, CooldownUpdatePacket::class.java)

        // Register items
        RockBottomAPI.ITEM_REGISTRY.register(PEARL_RESOURCE, PearlItem())
        RockBottomAPI.ITEM_REGISTRY.register(BOUNCY_PEARL_RESOURCE, BouncyPearlItem())
        RockBottomAPI.ITEM_REGISTRY.register(RIDEABLE_PEARL_RESOURCE, RideablePearlItem())

        // Register entities
        RockBottomAPI.ENTITY_REGISTRY.register(PEARL_RESOURCE, PearlEntity::class.java)
        RockBottomAPI.ENTITY_REGISTRY.register(BOUNCY_PEARL_RESOURCE, BouncyPearlEntity::class.java)
        RockBottomAPI.ENTITY_REGISTRY.register(RIDEABLE_PEARL_RESOURCE, RideablePearlEntity::class.java)
    }
}
