package earth.terrarium.lookinsharp.api.abilities;

import com.mojang.serialization.Codec;
import org.apache.commons.lang3.NotImplementedException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ToolAbilityManager {
    public static Codec<ToolAbility> CODEC = Codec.STRING.xmap(ToolAbilityManager::getAbility, ToolAbilityManager::getName);
    private static final Map<String, ToolAbility> ABILITY_MAP = new HashMap<>();

    public static final ToolAbility BLANK = registerAbility("spirit:blank", new ToolAbility() {
    });

    public static ToolAbility registerAbility(String name, ToolAbility ability) {
        ABILITY_MAP.put(name, ability);
        return ability;
    }

    public static ToolAbility getAbility(String name) {
        return ABILITY_MAP.get(name);
    }

    public static String getName(ToolAbility ability) {
        for (Map.Entry<String, ToolAbility> entry : ABILITY_MAP.entrySet()) {
            if (entry.getValue() == ability) {
                return entry.getKey();
            }
        }
        throw new NotImplementedException("Ability " + ability + " is not registered!");
    }

    public static List<AbilityEntry> getAbilityEntries() {
        return ABILITY_MAP.entrySet().stream().map(entry -> new AbilityEntry(entry.getKey(), entry.getValue())).collect(Collectors.toList());
    }

    public record AbilityEntry(String name, ToolAbility ability) { }
}
