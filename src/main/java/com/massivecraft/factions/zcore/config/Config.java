package com.massivecraft.factions.zcore.config;

import java.util.Arrays;
import java.util.List;

public enum Config {

    DYNMAP_ENABLED("Dynmap.enabled", false),
    DYNMAP_LAYER_NAME("Dynmap.layer-name", "Factions"),
    DYNMAP_LAYER_VISIBLE("Dynmap.layer-visible", true),
    DYNMAP_LAYER_PRIORITY("Dynmap.layer-priority", 2),
    DYNMAP_LAYER_MINZOOM("Dynmap.layer-min-zoom-visibility", 0),

    // Format for popup - substitute values for macros
    DYNMAP_DESC_POPUP("Dynmap.popup-description",
            "<div class=\"infowindow\">\n"
                    + "<span style=\"font-weight: bold; font-size: 150%;\">%name%</span><br>\n"
                    + "<span style=\"font-style: italic; font-size: 110%;\">%description%</span><br>"
                    + "<br>\n"
                    + "<span style=\"font-weight: bold;\">Leader:</span> %players.leader%<br>\n"
                    + "<span style=\"font-weight: bold;\">Admins:</span> %players.admins.count%<br>\n"
                    + "<span style=\"font-weight: bold;\">Moderators:</span> %players.moderators.count%<br>\n"
                    + "<span style=\"font-weight: bold;\">Members:</span> %players.normals.count%<br>\n"
                    + "<span style=\"font-weight: bold;\">TOTAL:</span> %players.count%<br>\n"
                    + "</br>\n"
                    + "<span style=\"font-weight: bold;\">Bank:</span> %money%<br>\n"
                    + "<br>\n"
                    + "</div>"),
    DYNMAP_DESC_MONEY("Dynmap.description-show.money", false),
    DYNMAP_VISIBILITY_BY_FACTION("Dynmap.visibility-by-faction", true),

    DYNMAP_VISIBLE_FACTIONS("Dynmap.visible-factions", new String[]{}),
    DYNMAP_HIDDEN_FACTIONS("Dynmap.hidden-factions", new String[]{}),

    DYNMAP_STYLE_LINE_COLOR("Dynmap.style.line-color", "#00FF00"),
    DYNMAP_STYLE_LINE_OPACITY("Dynmap.style.line-opacity", 0.8D),
    DYNMAP_STYLE_LINE_WEIGHT("Dynmap.style.line-weight", 3),
    DYNMAP_STYLE_FILL_COLOR("Dynmap.style.fill-color", "#00FF00"),
    DYNMAP_STYLE_FILL_OPACITY("Dynmap.style.fill-opacity", 0.35D),
    DYNMAP_STYLE_HOME_MARKER("Dynmap.style.home-marker", "greenflag"),
    DYNMAP_STYLE_BOOST("Dynmap.style.boost", false),

    CMD_ALIASES("General.base-command-aliases", new String[]{"f"}),
    SERVER_TIMEZONE("General.server-timezone", "EST"),
    CMD_ALLOW_NOSLASH("General.allow-noslash-command", false),
    AUTOSAVE_INTERVAL_MINUTES("General.autosave-interval-minutes", 30),
    AUTOPURGE_ENALBED("General.auto-purge.enabled", true),
    AUTOPURGE_INACTIVITY_DAYS("General.auto-purge.inactivity-days", 30),
    AUTOPURGE_INTERVAL_MINUTES("General.auto-purge.task-interval-minutes", 5),
    AUTOPURGE_REMOVE_BANNED("General.auto-purge.remove-banned-data", true),
    AUTOPURGE_REMOVE_BANNEDREASON("General.auto-purge.banned-reason-regex", new String[]{"Banned by admin", ".*cheating", ".*permanent"}),
    AUTOPURGE_REMOVE_DATA("General.auto-purge.remove-fplayer-data", true), // Let them just remove player from Faction.
    PRE_STARTUP_KICK("General.deny-join-before-data-fully-loaded", true),
    DATE_FORMAT("General.date-format", "HH:mm dd/MM/yyyy"),

    LOG_FCREATE("General.logs.faction-create", true),
    LOG_FDISBAND("General.logs.faction-disband", true),
    LOG_FJOIN("General.logs.faction-join", true),
    LOG_FKICK("General.logs.faction-kick", true),
    LOG_FLEAVE("General.logs.faction-leave", true),
    LOG_FCLAIM("General.logs.faction-claim", true),
    LOG_FUNCLAIM("General.logs.faction-unclaim", true),
    LOG_ECONOMY("General.logs.economy-transactions", true),
    LOG_COMMANDS("General.logs.player-commands", true),

    EXPLOIT_OBBYGEN("General.prevent-exploit.obsidian-generators", true),
    EXPLOIT_PEARLCLIP("General.prevent-exploit.enderpearl-clipping", true),
    EXPLOIT_INTERACTSPAM("General.prevent-exploit.interact-spam", true),
    EXPLOIT_TNTWATERLOG("General.prevent-exploit.tnt-waterlog", false),
    EXPLOIT_LIQUIDFLOW("General.prevent-exploit.liquid-flow", false),
    EXPLOIT_FINDFACTION("General.prevent-exploit.find-factions.enabled", true),
    EXPLOIT_FINDFACTION_COOLDOWN("General.prevent-exploit.find-factions.cooldown-ms", 2050),

    ACTION_DENIED_PAIN("General.action-denied-pain-amount", 1D),
    PLAYERS_BYPASSING_PROTECTIONS("General.players-bypassing-protections", new String[]{}), // mainly for other plugins/mods that use a fake player to take actions, which shouldn't be subject to our protections
    SEND_PERMISSION_HINTS("General.send-permission-hints", false),
    F_LOGOUT_COOLDOWN("General.f-logout-cooldown-seconds", 30),

    COLOR_MEMBER("Colors.member", "GREEN"),
    COLOR_ALLY("Colors.ally", "LIGHT_PURPLE"),
    COLOR_TRUCE("Colors.truce", "DARK_PURPLE"),
    COLOR_NEUTRAL("Colors.neutral", "WHITE"),
    COLOR_ENEMY("Colors.enemy", "RED"),
    COLOR_PEACEFUL("Colors.peaceful", "GOLD"),
    COLOR_WILDERNESS("Colors.wilderness", "GRAY"),
    COLOR_SAFEZONE("Colors.safezone", "GOLD"),
    COLOR_WARZONE("Colors.warzone", "DARK_RED"),

    POWER_PLAYER_MAX("Power.player-max", 10.0D),
    POWER_PLAYER_MIN("Power.player-min", -10.0),
    POWER_PLAYER_STARTING("Power.player-starting", 0.0),
    POWER_PER_MINUTE("Power.power-per-minute", 0.2), // Default health rate... it takes 5 min to heal one power
    POWER_PER_DEATH("Power.power-per-death", 4.0), // A death makes you lose 4 power
    POWER_REGEN_OFFLINE("Power.power-regen-offline", false), // does player power regenerate even while they're offline?
    POWER_OFFLINE_LOSSPERDAY("Power.offline-loss-per-day", 0.0), // players will lose this much power per day offline
    POWER_OFFLINE_LOSSLIMIT("Power.offline-loss-limit", 0.0), // players will no longer lose power from being offline once their power drops to this amount or less
    POWER_FACTION_MAX("Power.faction-max-power", 0.0), // if > 0, the cap on how much power a faction can have (power from players beyond that will act as a "buffer" of sorts)

    PREFIX_LEADER("Prefix.leader", "***"),
    PREFIX_COLEADER("Prefix.coleader", "**"),
    PREFIX_MOD("Prefix.mod", "*"),
    PREFIX_RECRUIT("Prefix.recruit", "-"),
    PREFIX_NORMAL("Prefix.normal", "+"),

    FACTION_TAG_LENGTH_MIN("Faction.tag.length-min", 3),
    FACTION_TAG_LENGTH_MAX("Faction.tag.length-max", 10),
    FACTION_TAG_FORCE_UPPERCASE("Faction.tag.force-uppercase", false),
    FACTION_TAG_BROADCAST_CHANGES("Faction.tag.broadcast-changes", false),
    FACTION_NEW_DEFAULT_OPEN("Faction.new-default-open", false),
    FACTION_NEW_DEFAULT_RELATION("Faction.new-default-relation", "NEUTRAL"),
    FACTION_NEGATIVEPOWER_LEAVE("Faction.can-leave-with-negative-power", true), // Disallow joining/leaving/kicking while power is negative

    FACTION_WARPS_LIMIT("Faction.warps.max-warps", 5),


    FACTION_ALT_ENABLED("Faction.alts.enabled", false),
    FACTION_ALT_LIMIT("Faction.alts.limit", 10),
    FACTION_ALT_HAVEPOWER("Faction.alts.have-power", false),

    FACTION_PAYPAL_ENABLED("Faction.paypal.enabled", false),
    FACTION_DISCORD_ENABLED("Faction.discord.enabled", false),

    FACTION_CHECKPOINTS_ENABLED("Faction.checkpoints.enabled", false),

    FACTION_MEMBER_LIMIT("Faction.member-limit", 30), // when faction membership hits this limit, players will no longer be able to join using /f join; default is 0, no limit
    FACTION_BUFFER_SIZE("Faction.buffer-size", 20),
    FACTION_SPAWNER_LOCK("Faction.spawner-lock", false),
    FACTION_NEW_PLAYERS_FID("Faction.new-players-starting-factionid", "0"), // what faction ID to start new players in when they first join the server; default is 0, "no faction"
    FACTION_BLACKLISTED_NAMES("Faction.blacklisted-names", new String[]{}),
    FACTION_GRACE_ENABLED("Faction.grace.enabled", true),
    FACTION_GRACE_BROADCAST("Faction.grace.broadcast-toggles", true),
    FACTION_GRACE_DAYS("Faction.grace.period-time-days", 7),

    FACTION_FRIENDLYFIRE_CMD_ENABLED("Faction.friendly-fire-command", false),
    FACTION_FILLCLAIM_MAXCOUNT("Faction.fillclaim.maxcount", 25),
    FACTION_FILLCLAIM_MAXDISTANCE("Faction.fillclaim.maxdistance", 5D),
    FACTION_CLAIMS_ONLYCONNECTED("Faction.claims-must-be-connected", false),
    FACTION_CLAIMS_UNCONNECTED_IF_OVERCLAIM("Faction.claims-can-be-unconnected-if-owned-by-other-faction", true),
    FACTION_CLAIMS_REQUIRED_MEMBERS("Faction.min-members-to-claim", 1),
    FACTION_CLAIMS_LIMIT("Faction.max-claims", 0),
    FACTION_LINECLAIM_LIMIT("Faction.line-claim-limit", 5),
    FACTION_CLAIMRADIUS_ENABLED("Faction.claimradius.enabled", false),
    FACTION_CLAIMRADIUS_LIMIT("Faction.claimradius.limit", 30),
    FACTION_CLAIMRADIUS_TRIES("Faction.claimradius.max-tries", 9),

    FACTION_DRAIN_ENABLED("Faction.drain.enabled", false),
    FACTION_DRAIN_COOLDOWN("Faction.drain.cooldown", 60),
    FACTION_RESERVE_ENABLED("Faction.reserve-enabled", false),
    FACTION_AUDIT_ENABLED("Faction.audit-enabled", false),
    FACTION_DISBANDGUI_USE("Faction.use-disband-gui", true),
    FACTION_STEALTH_ENABLED("Faction.stealth-enabled", false),
    FACTION_STRIKE_ENABLED("Faction.strikes-enabled", true),
    FACTION_OFFLINE_OFFSET_MINUTES("Faction.offline-offset-minutes", 5),
    FACTION_ALL_PEACEFUL("Faction.all-factions-are-peaceful", false),
    FACTION_PEACEFUL_NOMOBS("Faction.peaceful-disable-mobs", false),
    FACTION_PEACEFUL_NOTNT("Faction.peaceful-disable-explosions", false),
    FACTION_PEACEFUL_NOPOWERLOSS("Faction.peaceful-no-power-loss", true),
    FACTION_PERMANENT_NOLEADERPROMOTION("Faction.permanent-disable-leader-promotion", false),
    FACTION_PROTECT_ANIMALS("Faction.protect-animals-in-faction-claims", false),

    SPAWNERCHUNKS_ENABLED("SpawnerChunks.enabled", false),
    SPAWNERCHUNKS_MAXALLOWED("SpawnerChunks.max-allowed", 2),
    SPAWNERCHUNKS_ALLOW_UPGRADE("SpawnerChunks.allow-upgrade", true),
    SPAWNERCHUNKS_ALLOW_WILDERNESS("SpawnerChunks.allow-in-wilderness", false),
    SPAWNERCHUNKS_ALLOW_UNCLAIM("SpawnerChunks.allow-unclaim-with-spawners-in-chunk", true),
    SPAWNERCHUNKS_COLOR("SpawnerChunks.color", "BLUE"),
    SPAWNERCHUNKS_STRING("SpawnerChunks.name-string", "Spawner Chunk"),

    HOMES_ENABLED("Homes.enabled", true),
    HOMES_ONLY_IN_CLAIMS("Homes.allow-only-in-claims", true),
    HOMES_RESPAWN_ONDEATH("Homes.teleport-to-on-death", false),
    HOMES_RESPAWN_NOPOWERLOSSWORLDS("Homes.respawn-from-no-powerloss-worlds", false),
    HOMES_TELEPORT_CMD_ENABLED("Homes.teleport-command.enabled", true),
    HOMES_TELEPORT_CMD_EFFECTS("Homes.teleport-command.smoke-effect-enabled", true),
    HOMES_TELEPORT_CMD_EFFECT_THICC("Homes.teleport-command.smoke-effect-thickness", 3),
    HOMES_TP_FROM_ENEMY_CLAIMS("Homes.allow-teleport-from-enemy-claims", false),
    HOMES_TP_FROM_OTHER_WORLD("Homes.allow-teleport-from-other-world", true),
    HOMES_TP_ENEMY_DISTANCE("Homes.deny-teleport-enemy-distance", 32D),
    HOMES_TP_IGNOREENEMIES_OWNCLAIM("Homes.ignore-enemies-in-own-claims", true),
    HOMES_TP_IGNOREENEMIES_NOCLAIMWORLD("Homes.ignore-enemies-if-claiming-disabled", true),

    WORLDS_AS_BLACKLIST("Worlds.consider-as-blacklist", false), // if true = blacklist
    WORLDS_CLAIMING("Worlds.claiming-worlds", new String[]{"world1", "world2"}),
    WORLDS_FACTIONS("Worlds.factions-plugin-worlds", new String[]{"world1", "world2"}),
    WORLDS_HOMETP("Worlds.home-teleport-worlds", new String[]{"world1", "world2"}),
    WORLDS_HANDLEPVP("Worlds.handle-pvp-worlds", new String[]{"world1", "world2"}),
    WORLDS_POWERLOSS("Worlds.powerloss-worlds", new String[]{"world1", "world2"}),
    WORLDS_WILDERNESS("Worlds.wilderness-protection-worlds", new String[]{"world1", "world2"}),

    // useWorldConfigurationsAsWhitelist
    // worldsIgnorePvP
    //

    MAP_SHOW_FACTION_KEY("Map.show-faction-key", true),
    MAP_SHOW_NEUTRAL_FACTIONS("Map.show-neutral-factions", true),
    MAP_SHOW_ENEMY_FACTIONS("Map.show-enemy-factions", true),
    MAP_SHOW_TRUCE_FACTIONS("Map.show-truce-factions", true),
    MAP_CLICK_TO_CLAIM("Map.click-to-claim-enabled", true),
    MAP_HEIGHT("Map.height", 17),
    MAP_WIDTH("Map.width", 49),
    MAP_KEY_CHARS("Map.key-chars", "\\/#$%=&^ABCDEFGHJKLMNOPQRSTUVWXYZ1234567890abcdeghjmnopqrsuvwxyz?"),

    CHAT_FACTION_ONLY("Chat.faction-only", true),
    CHAT_TAG_ENABLED("Chat.tag-enabled", true),
    CHAT_TAG_OTHERPLUGIN("Chat.tag-handled-by-otherplugin", false),
    CHAT_TAG_RELATION_COLORED("Chat.tag-relation-colored", true),

    CHAT_TAG_FORMAT("Chat.tag-format", "%s&f"),
    CHAT_FC_FORMAT("Chat.faction-chat-format", "%s:&f %s"),
    CHAT_AC_FORMAT("Chat.alliance-chat-format", "&d%s:%f %s"),
    CHAT_TC_FORMAT("Chat.truce-chat-format", "&5%s:&f %s"),
    CHAT_MOD_FORMAT("Chat.mod-chat-format", "&c%s:&f %s"),

    FLY_ENABLED("Fly.enabled", false),
    FLY_AUTO_ENABLE("Fly.auto-enable", true),
    FLY_COMBAT_COOLDOWN("Fly.deny-fly-for-x-seconds-after-combat", 15),
    FLY_DISABLE_ON_PVP("Fly.disable-on-pvp", true),
    FLY_DISABLE_ON_PVE("Fly.disable-on-pve", false),
    FLY_STEALTH_CHECK_RADIUS("Fly.stealth-check-radius", 32D),
    FLY_DENY_ENDERPEARLS("Fly.deny-enderpearls", true),
    FLY_DISABLE_ON_CLAIMCHANGE("Fly.disable-on-claim-change", true),
    FLY_FALLDAMAGE_COOLDOWN("Fly.bypass-falldamage-for-x-seconds", 10),
    FLY_NEARBY_ENEMIES_CANCEL("Fly.nearby-enemies-disable-flight", true),


    WORLDGUARD_ENABLED("WorldGuard.checking-enabled", false),
    WORLDGUARD_BUILD_PRIORITY("WorldGuard.build-priority", false),

    WORLDBORDER_BUFFER("WorldBorder.chunks-buffer-from-border", 0),

    PVP_DISABLE_BETWEEN_NEUTRAL("PvP.disable-between-neutral-factions", false),
    PVP_DISABLE_FOR_FACTIONLESS("PvP.disable-for-factionless-players", false),
    PVP_HURT_FACTIONLESS_IN_CLAIMS("PvP.enable-against-factionless-in-attackers-claims", true),
    PVP_DISABLED_LOGIN_OFFSET("PvP.disable-for-x-seconds-after-login", 3),
    PVP_DISABLED_PEACEFUL_CLAIMS("PvP.disable-in-peaceful-claims", true),

    DENY_CMD_PERMANENT_FACTION("DenyCommands.permanent-faction-member", new String[]{}),
    DENY_CMD_NEUTRAL_CLAIM("DenyCommands.inside-neutral-claims", new String[]{}),
    DENY_CMD_ENEMY_CLAIM("DenyCommands.inside-enemy-claims", new String[]{
            "home", "ehome", "homes", "sethome", "esethome", "createhome", "ecreatehome", "spawn", "espawn", "tpahere", "etpahere", "tpaccept", "etpaccept", "tpyes", "etpyes", "call", "ecall", "tpa", "etpa", "etpask"
    }),
    DENY_CMD_ALLY_CLAIM("DenyCommands.inside-ally-claims", new String[]{}),
    DENY_CMD_WARZONE("DenyCommands.inside-warzone", new String[]{}),
    DENY_CMD_WILDERNESS("DenyCommands.inside-wilderness", new String[]{}),

    CLAIMS_DENYBUILD("Territory.claims.deny-build", true),
    CLAIMS_DENYBUILD_OFFLINE("Territory.claims.deny-build-when-offline", true),
    CLAIMS_PAINBUILD("Territory.claims.pain-build", false),
    CLAIMS_PAINBUILD_OFFLINE("Territory.claims.pain-build-when-offline", false),
    CLAIMS_DENYUSAGE("Territory.claims.deny-usage", true),
    CLAIMS_ENEMY_DENYBUILD("Territory.claims.enemy-deny-build", true),
    CLAIMS_ENEMY_DENYBUILD_OFFLINE("Territory.claims.enemy-deny-build-when-offline", true),
    CLAIMS_ENEMY_PAINBUILD("Territory.claims.enemy-pain-build", false),
    CLAIMS_ENEMY_PAINBUILD_OFFLINE("Territory.claims.enemy-pain-build-when-offline", false),
    CLAIMS_ENEMY_DENYUSAGE("Territory.claims.enemy-deny-usage", true),

    CLAIMS_ALLY_DENYBUILD("Territory.claims.ally-deny-build", true),
    CLAIMS_ALLY_DENYBUILD_OFFLINE("Territory.claims.ally-deny-build-when-offline", true),
    CLAIMS_ALLY_PAINBUILD("Territory.claims.ally-pain-build", false),
    CLAIMS_ALLY_PAINBUILD_OFFLINE("Territory.claims.ally-pain-build-when-offline", false),
    CLAIMS_ALLY_DENYUSAGE("Territory.claims.ally-deny-usage", true),

    CLAIMS_TRUCE_DENYBUILD("Territory.claims.truce-deny-build", true),
    CLAIMS_TRUCE_DENYBUILD_OFFLINE("Territory.claims.truce-deny-build-when-offline", true),
    CLAIMS_TRUCE_PAINBUILD("Territory.claims.truce-pain-build", false),
    CLAIMS_TRUCE_PAINBUILD_OFFLINE("Territory.claims.truce-pain-build-when-offline", false),
    CLAIMS_TRUCE_DENYUSAGE("Territory.claims.truce-deny-usage", true),

    CLAIMS_BLOCK_CREEPERS("Territory.claims.block-creepers", false),
    CLAIMS_BLOCK_CREEPERS_OFFLINE("Territory.claims.block-creepers-when-offline", false),
    CLAIMS_BLOCK_FIREBALLS("Territory.claims.block-fireballs", false),
    CLAIMS_BLOCK_FIREBALLS_OFFLINE("Territory.claims.block-fireballs-when-offline", false),
    CLAIMS_BLOCK_TNT("Territory.claims.block-tnt", false),
    CLAIMS_BLOCK_TNT_OFFLINE("Territory.claims.block-tnt-when-offline", false),
    CLAIMS_BLOCK_ENDERMAN("Territory.claims.block-enderman-grief", false),
    CLAIMS_BLOCK_ENDERMAN_OFFLINE("Territory.claims.block-enderman-grief-when-offline", false),

    CLAIMS_SAFEZONE_DENYBUILD("Territory.safezone.deny-build", true),
    CLAIMS_SAFEZONE_DENYUSAGE("Territory.safezone.deny-usage", true),
    CLAIMS_SAFEZONE_DENYDAMAGE("Territory.safezone.deny-damage", false),
    CLAIMS_SAFEZONE_BLOCK_TNT("Territory.safezone.block-tnt", true),
    CLAIMS_SAFEZONE_BLOCK_ENDERMAN("Territory.safezone.block-enderman-grief", true),
    CLAIMS_SAFEZONE_NERFED_MOBS("Territory.safezone.nerfed-mobs", new String[]{
            "PIG_ZOMBIE", "ZOMBIFIED_PIGLIN", "AXOLOTL", "GLOW_SQUID", "BLAZE", "CAVE_SPIDER", "CREEPER", "ENDER_DRAGON", "ENDERMAN", "GHAST", "MAGMA_CUBE", "SILVERFISH", "SKELETON", "SPIDER", "SLIME", "WITCH", "WITHER", "ZOMBIE"
    }),

    CLAIMS_WARZONE_DENYBUILD("Territory.warzone.deny-build", true),
    CLAIMS_WARZONE_DENYUSAGE("Territory.warzone.deny-usage", true),
    CLAIMS_WARZONE_BLOCK_TNT("Territory.warzone.block-tnt", true),
    CLAIMS_WARZONE_BLOCK_CREEPERS("Territory.warzone.block-creepers", false),
    CLAIMS_WARZONE_BLOCK_FIREBALLS("Territory.warzone.block-fireballs", false),
    CLAIMS_WARZONE_BLOCK_ENDERMAN("Territory.warzone.block-enderman-grief", true),
    CLAIMS_WARZONE_FRIENDLYFIRE("Territory.warzone.friendlyfire-enabled", false),
    CLAIMS_WARZONE_POWERLOSS("Territory.warzone.powerloss-enabled", true),

    CLAIMS_WILD_DENYBUILD("Territory.wilderness.deny-build", true),
    CLAIMS_WILD_DENYUSAGE("Territory.wilderness.deny-usage", true),
    CLAIMS_WILD_BLOCK_TNT("Territory.wilderness.block-tnt", true),
    CLAIMS_WILD_BLOCK_CREEPERS("Territory.wilderness.block-creepers", false),
    CLAIMS_WILD_BLOCK_FIREBALLS("Territory.wilderness.block-fireballs", false),
    CLAIMS_WILD_BLOCK_ENDERMAN("Territory.wilderness.block-enderman-grief", true),
    CLAIMS_WILD_POWERLOSS("Territory.wilderness.powerloss-enabled", true),

    CLAIMS_DENYBUILD_MATERIALS("Territory.deny-build-materials", new String[]{"BEACON"}),
    CLAIMS_DENYUSAGE_MATERIALS("Territory.deny-usage-materials", new String[]{}),
    CLAIMS_BYPASSPROTECTION_MATERIALS("Territory.bypass-protection-materials", new String[]{}),
    CLAIMS_DENYBUILD_MATERIALS_OFFLINE("Territory.deny-build-materials-when-offline", new String[]{"BEACON"}),
    CLAIMS_DENYUSAGE_MATERIALS_OFFLINE("Territory.deny-usage-materials-when-offline", new String[]{
            "FIRE_CHARGE", "FLINT_AND_STEEL", "BUCKET", "LAVA_BUCKET", "WATER_BUCKET", "ARMOR_STAND", "COD_BUCKET", "PUFFERFISH_BUCKET", "SALMON_BUCKET", "TROPICAL_FISH_BUCKET", "AXOLOTL_BUCKET", "POWDER_SNOW_BUCKET"
    }),

    CLAIMS_DENYBUILD_PISTONPROTECTION("Territory.piston-protection-through-deny-build", true),
    CLAIMS_ALLOW_CREEPEREGG_CHEST("Territory.allow-creeperegging-chests", true),
    CLAIMS_DISABLE_PISTONS("Territory.disable-pistons", false),
    CLAIMS_LIMIT_PORTALS("Territory.limit-portal-creation.enabled", true),
    CLAIMS_LIMIT_PORTALS_WILD("Territory.limit-portal-creation.deny-in-wilderness", true),
    CLAIMS_LIMIT_PORTALS_MINREL("Territory.limit-portal-creation.minimum-relation", "MEMBER"),

    OWNED_CHUNKS_ENABLED("OwnedChunks.enabled", true),
    OWNED_CHUNKS_LIMIT("OwnedChunks.limit-per-faction", 0),
    OWNED_CHUNKS_MODSCANSET("OwnedChunks.moderators-can-set", false),
    OWNED_CHUNKS_MODSBYPASS("OwnedChunks.moderators-bypass", true),
    OWNED_CHUNKS_DENYBUILD("OwnedChunks.deny-build", true),
    OWNED_CHUNKS_PAINBUILD("OwnedChunks.pain-build", false),
    OWNED_CHUNKS_NOTIFY_ONBORDER("OwnedChunks.notify-on-border", true),
    OWNED_CHUNKS_NOTIFY_INSIDE("OwnedChunks.notify-inside", true),
    OWNED_CHUNKS_NOTIFY_BYCHUNK("OwnedChunks.notify-by-chunk", false),

    ECON_ENABLED("Economy.enabled", false),
    ECON_ACCOUNT("Economy.universe-account", ""),
    ECON_COST_CLAIM("Economy.claim.price", 30D),
    ECON_START_BALANCE("Economy.starting-balance", 0D),
    ECON_COST_OVERCLAIM_BONUS("Economy.claim.overclaim-bonus", 0D),
    ECON_OVERCLAIM_REWARD_MULTIPLIER("Economy.claim.overclaim-reward-multiplier", 0D),
    ECON_CLAIM_ADDITIONAL_MULTIPLIER("Economy.claim.additional-multiplier", 0.5),
    ECON_CLAIM_REFUND_MULTIPLIER("Economy.claim.refund-multiplier", 0.7),
    ECON_CLAIM_UNCONNECTED_FEE("Economy.claim.unconnected-fee", 0.0),
    ECON_COST_CREATE("Economy.cmd-price.create-faction", 100D),
    ECON_COST_OWNER("Economy.cmd-price.chunk-owner", 15D),
    ECON_COST_SETHOME("Economy.cmd-price.sethome", 30D),
    ECON_COST_JOIN("Economy.cmd-price.join", 0D),
    ECON_COST_LEAVE("Economy.cmd-price.leave", 0D),
    ECON_COST_KICK("Economy.cmd-price.kick", 0D),
    ECON_COST_INVITE("Economy.cmd-price.invite", 0D),
    ECON_COST_HOME("Economy.cmd-price.home", 0D),
    ECON_COST_RENAME("Economy.cmd-price.tag", 0D),
    ECON_COST_DESC("Economy.cmd-price.description", 0D),
    ECON_COST_TITLE("Economy.cmd-price.title", 0D),
    ECON_COST_LIST("Economy.cmd-price.list", 0D),
    ECON_COST_MAP("Economy.cmd-price.map", 0D),
    ECON_COST_POWER("Economy.cmd-price.power", 0D),
    ECON_COST_SHOW("Economy.cmd-price.show", 0D),
    ECON_COST_STUCK("Economy.cmd-price.stuck", 0D),
    ECON_COST_OPEN("Economy.cmd-price.open", 0D),
    ECON_COST_ALLY("Economy.cmd-price.ally", 0D),
    ECON_COST_TRUCE("Economy.cmd-price.truce", 0D),
    ECON_COST_ENEMY("Economy.cmd-price.enemy", 0D),
    ECON_COST_NEUTRAL("Economy.cmd-price.neutral", 0D),
    ECON_COST_NOBOOM("Economy.cmd-price.noboom", 0D),
    ECON_COST_SETWARP("Economy.cmd-price.setwarp", 5D),
    ECON_COST_DELWARP("Economy.cmd-price.delwarp", 5D),
    ECON_COST_WARP("Economy.cmd-price.warp", 5D),

    //Faction banks, to pay for land claiming and other costs instead of individuals paying for them
    ECON_BANK_ENABLED("Economy.bank.enabled", true),
    ECON_BANK_MEMBERS_WITHDRAW("Economy.bank.members-can-withdraw", false), //Have to be at least moderator to withdraw or pay money to another faction
    ECON_BANK_PAYS_COSTS("Economy.bank.faction-pays-costs", true), //The faction pays for faction command costs, such as sethome
    ECON_BANK_PAYS_CLAIM_COSTS("Economy.bank.faction-pays-claim-costs", true),  //The faction pays for land claiming costs.

    FACTION_LOCKED_PERMS_ENABLED("Faction.locked-permissions.enabled", false),
    FACTION_LOCKED_PERMS("Faction.locked-permissions.list", new String[]{"CHEST", "TRAPPED_CHEST"}),
    FACTION_DEFAULT_MAXVAULTS("Faction.max-vaults-default", 0),
    FACTION_WEEWOO_INTERVAL_SECONDS("Faction.weewoo-broadcast-interval-seconds", 30),

    FACTION_BROADCAST_CREATE("Faction.broadcast.on-creation", true),
    FACTION_BROADCAST_OPEN("Faction.broadcast.on-opening", false),
    FACTION_BROADCAST_DISBAND("Faction.broadcast.on-disband", true),
    FACTION_BROADCAST_LEADER("Faction.broadcast.on-leader-change", false),

    // Default Faction Permission Settings.
    // boolean useCustomDefaultPermissions = true;
    // HashMap<String, DefaultPermissions> defaultFactionPermissions = new HashMap<>();
/*
    static {
        // Is this called lazy load?
        defaultFactionPermissions.put("COLEADER", new DefaultPermissions(true));
        defaultFactionPermissions.put("MODERATOR", new DefaultPermissions(true));
        defaultFactionPermissions.put("NORMAL MEMBER", new DefaultPermissions(false));
        defaultFactionPermissions.put("RECRUIT", new DefaultPermissions(false));
        defaultFactionPermissions.put("ALLY", new DefaultPermissions(false));
        defaultFactionPermissions.put("ENEMY", new DefaultPermissions(false));
        defaultFactionPermissions.put("TRUCE", new DefaultPermissions(false));
        defaultFactionPermissions.put("NEUTRAL", new DefaultPermissions(false));
    }

 */


    DEBUG("Debug", false);


    String config, message;
    Boolean option;
    String[] messages;
    Integer number;
    Double dnumber;

    Config(String config, String message) {
        this.config = config;
        this.message = message;
    }

    Config(String config, String[] messages) {
        this.config = config;
        this.messages = messages;
    }

    Config(String config, Boolean option) {
        this.config = config;
        this.option = option;
    }

    Config(String config, Integer number) {
        this.config = config;
        this.number = number;
    }

    Config(String config, Double dnumber) {
        this.config = config;
        this.dnumber = dnumber;
    }

    public boolean getOption() {
        return option;
    }

    public String getConfig() {
        return config;
    }

    public String getString() {
        return message;
    }

    public Double getDouble() {
        return dnumber;
    }

    public Integer getInt() {
        return number;
    }

    public String[] getStrings() {
        return this.messages;
    }

    public List<String> getStringList() {
        return Arrays.asList(this.messages);
    }

    public void setInt(int number) {
        this.number = number;
    }

    public void setDouble(double dnumber) {
        this.dnumber = dnumber;
    }

    public void setStrings(List<String> list) {
        this.messages = list.toArray(new String[0]);
    }

    public void setString(String message) {
        this.message = message;
    }

    public void setOption(Boolean option) {
        this.option = option;
    }
}
