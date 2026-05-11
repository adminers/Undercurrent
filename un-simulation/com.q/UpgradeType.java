package com.trolleygame.upgrades;

/**
 * 升级类型枚举 - 对应原JS版本中的升级系统
 */
public enum UpgradeType {
    // 基础升级
    HAND_CAPACITY_1("hand_capacity_1", "Hand Capacity", "Increase grab capacity"),
    HAND_REACH_1("hand_reach_1", "Hand Reach", "Increase grab reach"),
    TROLLEY_SPEED_1("trolley_speed_1", "Trolley Speed", "Increase trolley speed"),
    NPC_COUNT_1("npc_count_1", "NPC Count", "Increase NPC spawn count"),
    LEVER_ENERGY_1("lever_energy_1", "Lever Energy", "Increase lever energy"),
    COIN_INCOME_1("coin_income_1", "Coin Income", "Increase coin income"),
    NPC_VALUE_1("npc_value_1", "NPC Value", "Increase NPC value"),
    
    // 解锁类升级
    TROLLEY_UNLOCK("trolley_unlock", "Trolley Unlock", "Unlock trolley system"),
    GOLDEN_RAILS_UNLOCK("golden_rails_unlock", "Golden Rails", "Unlock golden rails"),
    OVERDRIVE_UNLOCK("overdrive_unlock", "Overdrive", "Unlock overdrive mode"),
    BLADES_UNLOCK("blades_unlock", "Blades", "Unlock blade system"),
    
    // 高级升级
    NPC_RESPAWN_1("npc_respawn_1", "NPC Respawn", "Increase NPC respawn rate"),
    UNLOCK_RICH_1("unlock_rich_1", "Rich NPCs", "Unlock rich NPCs"),
    NPC_COUNT_2("npc_count_2", "More NPCs", "Additional NPC count"),
    OVERDRIVE_DURATION_1("overdrive_duration_1", "Overdrive Duration", "Increase overdrive duration"),
    OVERDRIVE_DAMAGE_1("overdrive_damage_1", "Overdrive Damage", "Increase overdrive damage"),
    
    // 其他升级
    DANGEROUS_RAILS_1("dangerous_rails_1", "Dangerous Rails", "Dangerous rails upgrade"),
    STICKY_RAILS_1("sticky_rails_1", "Sticky Rails", "Sticky rails upgrade"),
    UNLOCK_RARE_1("unlock_rare_1", "Rare NPCs", "Unlock rare NPCs"),
    HAND_CAPACITY_2("hand_capacity_2", "Hand Capacity 2", "Advanced hand capacity"),
    AUTO_GRAB_COUNT_1("auto_grab_count_1", "Auto Grab", "Auto grab system"),
    AUTO_GRAB_SPEED_1("auto_grab_speed_1", "Auto Grab Speed", "Auto grab speed"),
    BLADES_SIZE_1("blades_size_1", "Blade Size", "Increase blade size"),
    BLADES_COOLDOWN_1("blades_cooldown_1", "Blade Cooldown", "Reduce blade cooldown"),
    BLADES_DURATION_1("blades_duration_1", "Blade Duration", "Increase blade duration"),
    
    // VIP和高级NPC
    NPC_VALUE_RICH_1("npc_value_rich_1", "Rich NPC Value", "Rich NPC value increase"),
    NPC_VALUE_VIP_1("npc_value_vip_1", "VIP NPC Value", "VIP NPC value increase"),
    TROLLEY_SPEED_2("trolley_speed_2", "Trolley Speed 2", "Advanced trolley speed"),
    HAND_REACH_2("hand_reach_2", "Hand Reach 2", "Advanced hand reach"),
    LEVER_ENERGY_2("lever_energy_2", "Lever Energy 2", "Advanced lever energy"),
    COIN_INCOME_2("coin_income_2", "Coin Income 2", "Advanced coin income"),
    HAND_CAPACITY_3("hand_capacity_3", "Hand Capacity 3", "Master hand capacity"),
    NPC_RESPAWN_2("npc_respawn_2", "NPC Respawn 2", "Advanced NPC respawn");
    
    private final String id;
    private final String name;
    private final String description;
    
    UpgradeType(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    
    public String getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    
    /**
     * 根据ID查找升级类型
     */
    public static UpgradeType fromId(String id) {
        for (UpgradeType type : values()) {
            if (type.id.equals(id)) {
                return type;
            }
        }
        return null;
    }
}
