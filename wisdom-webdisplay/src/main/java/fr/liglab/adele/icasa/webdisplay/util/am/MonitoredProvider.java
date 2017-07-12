package fr.liglab.adele.icasa.webdisplay.util.am;

import fr.liglab.adele.cream.model.introspection.EntityProvider;
import fr.liglab.adele.cream.model.introspection.RelationProvider;

import java.util.HashSet;
import java.util.Set;

/*ToDo*/
public class MonitoredProvider {

    String name;

    Set<MonitoredCreator> monitoredCreatorSet;


    public MonitoredProvider(EntityProvider entityProvider){
        this.name = entityProvider.getName();

        monitoredCreatorSet = new HashSet<MonitoredCreator>();
        for(String creator : entityProvider.getProvidedEntities()){
            MonitoredCreator monitoredCreator = new MonitoredCreator(creator, entityProvider.getOrigin(creator),
                    entityProvider.isEnabled(creator), entityProvider.getPotentiallyProvidedEntityServices(creator),
                    entityProvider.getPotentiallyProvidedEntityServices(creator), entityProvider.getInstances(creator, true)
            );
            monitoredCreatorSet.add(monitoredCreator);
        }
    }

    public MonitoredProvider(RelationProvider relationProvider){
        this.name = relationProvider.getName();

        monitoredCreatorSet = new HashSet<MonitoredCreator>();
        for(String creator : relationProvider.getProvidedRelations()){
            MonitoredCreator monitoredCreator = new MonitoredCreator(creator, relationProvider.getOrigin(creator),
                    relationProvider.isEnabled(creator), relationProvider.getPotentiallyProvidedRelationServices(creator),
                    relationProvider.getPotentiallyProvidedRelationServices(creator), relationProvider.getInstances(creator, true)
            );
            monitoredCreatorSet.add(monitoredCreator);
        }
    }
}