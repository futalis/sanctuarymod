package com.futalis.sanctuary.handlers;

import com.futalis.sanctuary.Sanctuary;
import com.futalis.sanctuary.tiles.SanctuaryBlockReference;
import com.futalis.sanctuary.util.LogHelper;
import net.minecraft.entity.EnumCreatureType;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;

public class ServerEventHandler {

    private boolean TestRange(SanctuaryBlockReference ref, double x, double y, double z, int dim){
        //cast (double) coordinates for an entity spawn, which will be in the middle of the block, do an (int) for more accurate range calculation.
        // could have gone the other way and cast everything to (double) and added .5 to each MobInhibitorReference coordinate, but I figured
        // integer math would be slightly faster than double math.
        //if (ref.dim != dim) { return false;} // Ignore any inhibitors in other dimensions
        int i = (int)(x-.5);
        int j = (int)(y-.5);
        int k = (int)(z-.5);
        int method = 0;//ConfigurationHandler.intMethod;
        int rad = 128;//ConfigurationHandler.EffectRadius;
        switch (method) {
            case 0: // sphere - dx^2+dy^2+dz^2 <= rad^2
                return (((ref.i- i)*(ref.i- i))   +   ((ref.j- j)*(ref.j- j))   +   ((ref.k- k)*(ref.k- k))   <=   (rad*rad));
            case 1: // cube - x,y,z all between Ref + or - radius
                return (((i >=ref.i-rad) && (i <=ref.i+rad))   &&   ((j >=ref.j-rad) && (j <=ref.j+rad))   &&   ((k >=ref.k-rad) && (k <=ref.k+rad)));
            case 2: // cylinder - dx^2+dz^2 <= rad^2 && y between Ref + or - radius
                return ((((ref.i- i)*(ref.i- i))  +  ((ref.k- k)*(ref.k- k))  <=  (rad*rad))  &&  ((j >=ref.j-rad) && (j <=ref.j+rad)));
            case 3: // column - same as Cylinder, but no height requirement.
                return (((ref.i- i)*(ref.i- i))  +  ((ref.k- k)*(ref.k- k))<=(rad*rad));
        }
        return false;
    }

    @SubscribeEvent
    public void BlockSpawnEvent(LivingSpawnEvent.CheckSpawn event){
        List<SanctuaryBlockReference> RefList = Sanctuary.Stage1;
        //if (event.getResult() != Event.Result.DEFAULT){ return;} // If the event is already forced Allow or Deny, let it through
        /*if (event.getEntity().isCreatureType(EnumCreatureType.MONSTER,false)){ //decide which list to use.
            RefList = Sanctuary.Stage1;
        } else if (event.entity.isCreatureType(EnumCreatureType.waterCreature,false)){
            RefList = MobInhibitor.AquaInhibitors;
        } else { // this will also catch ambient creatures like bats.
            if (!ConfigurationHandler.InhibitAmbient && event.entity.isCreatureType(EnumCreatureType.ambient,false)){
                return; //If the InhibitAmbient config is not set, and the creature type is ambient, don't do anything.
                //If the config is set, fall through. The remaining category is Creature, which includes passives.
            }
            RefList = MobInhibitor.PassiveInhibitors;
        }*/
        for (SanctuaryBlockReference Ref : RefList){
            if (TestRange(Ref, event.getX(), event.getY(), event.getZ(), event.getWorld().provider.getDimension())) {
                event.setResult(Event.Result.DENY);
                //LogHelper.debug("Blocked a spawn based on inhibitor at:"+Ref.i+", "+Ref.j+", "+Ref.k);
                //System.out.println("Blocked a spawn based on inhibitor at:"+Ref.i+", "+Ref.j+", "+Ref.k);
                return;
            }
        }
    }

    @SubscribeEvent
    public void BlockMobTeleport(EnderTeleportEvent event){
        if (!event.isCancelable()){return;}
        for (SanctuaryBlockReference Ref : Sanctuary.Stage1){
            if (TestRange(Ref, event.getTargetX(), event.getTargetY(), event.getTargetZ(), event.getEntityLiving().getEntityWorld().provider.getDimension())) {
                event.setCanceled(true);
                //LogHelper.debug("Blocked an Enderman teleport based on inhibitor at:"+Ref.i+", "+Ref.j+", "+Ref.k);
                break;
            }
        }

    }
}
