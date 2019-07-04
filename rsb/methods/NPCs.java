package net.runelite.client.rsb.methods;

import net.runelite.api.NPC;
import net.runelite.client.rsb.internal.wrappers.Filter;
import net.runelite.client.rsb.wrappers.RSNPC;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Provides access to non-player characters.
 */
public class NPCs extends MethodProvider {

    /**
     * A filter that accepts all matches.
     */
    public static final Filter<RSNPC> ALL_FILTER = new Filter<RSNPC>() {
        @Override
        public boolean accept(RSNPC npc) {
            return true;
        }
    };

    NPCs(final MethodContext ctx) {
        super(ctx);
    }

    /**
     * Returns an array of all loaded RSNPCs.
     *
     * @return An array of the loaded RSNPCs.
     */
    public RSNPC[] getAll() {
        return getAll(NPCs.ALL_FILTER);
    }

    /**
     * Returns an array of all loaded RSNPCs that are accepted by the provided
     * Filter
     *
     * @param filter Filters out unwanted matches.
     * @return An array of the loaded RSNPCs.
     */
    public RSNPC[] getAll(final Filter<RSNPC> filter) {
        NPC[] npcs = getNPCs();
        Set<RSNPC> rsNPCs = new HashSet<RSNPC>();
        for (NPC npc : npcs) {
            if (filter.accept((RSNPC) npc)) {
                rsNPCs.add((RSNPC) npc);
            }
        }
        return rsNPCs.toArray(new RSNPC[rsNPCs.size()]);
    }

    /**
     * Returns the RSNPC that is nearest out of all of loaded RSPNCs accepted by
     * the provided Filter.
     *
     * @param filter Filters out unwanted matches.
     * @return An RSNPC object representing the nearest RSNPC accepted by the
     * provided Filter; or null if there are no matching NPCs in the
     * current region.
     */
    public RSNPC getNearest(final Filter<RSNPC> filter) {
        int min = 20;
        RSNPC closest = null;
        NPC[] npcs = getNPCs();
        for (NPC npc : npcs) {
                RSNPC rsnpc = new RSNPC(methods, npc);
                if (filter.accept(rsnpc)) {
                    int distance = methods.calc.distanceTo(rsnpc);
                    if (distance < min) {
                        min = distance;
                        closest = rsnpc;
                    }
                }
            }
        return closest;
    }

    public NPC[] getNPCs() {
        List<NPC> npcs = methods.client.getNpcs();
        NPC[] npcArray = new NPC[npcs.size()];
        return npcs.toArray(npcArray);
    }

    /**
     * Returns the RSNPC that is nearest out of all of the RSPNCs with the
     * provided ID(s). Can return null.
     *
     * @param ids Allowed NPC IDs.
     * @return An RSNPC object representing the nearest RSNPC with one of the
     * provided IDs; or null if there are no matching NPCs in the
     * current region.
     */
    public RSNPC getNearest(final int... ids) {
        return getNearest(new Filter<RSNPC>() {
            @Override
            public boolean accept(RSNPC npc) {
                for (int id : ids) {
                    if (npc.getID() == id) {
                        return true;
                    }
                }
                return false;
            }
        });
    }

    /**
     * Returns the RSNPC that is nearest out of all of the RSPNCs with the
     * provided name(s). Can return null.
     *
     * @param names Allowed NPC names.
     * @return An RSNPC object representing the nearest RSNPC with one of the
     * provided names; or null if there are no matching NPCs in the
     * current region.
     */
    public RSNPC getNearest(final String... names) {
        return getNearest(new Filter<RSNPC>() {
            @Override
            public boolean accept(RSNPC npc) {
                for (String name : names) {
                    if (npc.getName().equals(name)) {
                        return true;
                    }
                }
                return false;
            }
        });
    }
}
