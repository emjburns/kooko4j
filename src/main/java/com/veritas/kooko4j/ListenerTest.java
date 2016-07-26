package com.veritas.kooko4j;

import org.openstack4j.api.Builders;
import org.openstack4j.api.OSClient;
import org.openstack4j.model.network.ext.Listener;
import org.openstack4j.model.network.ext.LoadBalancerV2;
import org.openstack4j.model.network.ext.Protocol;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListenerTest extends CommonTest{
    public void runTest(OSClient.OSClientV2 os){


        log("Testing Listener");

        boolean runtest = false;

        if (runtest){
            log("listener list");
            List<? extends Listener> list = os.networking().lbaasV2().listener().list();
            log(list);
        }

        if (runtest){
            log("Listener list");
            Map<String,String> m = new HashMap<String,String>();
            m.put("name", "listener1");
            List<? extends LoadBalancerV2> list = os.networking().lbaasV2().loadbalancerV2().list(m);
            log(list);
        }

        if (runtest){
            log("Listener create");
            Listener listener = os.networking().lbaasV2().listener()
                    .create(Builders.listenerV2()
                            .name("listener44")
                            .description("listener here")
                            .protocol(Protocol.HTTP)
                            .protocolPort(80)
                            .loadBalancerId("282b71ea-9ceb-4cd6-8881-cb511af2edb5")
                            .build());
            log(listener);
            waitforit();
        }

        if (runtest){
            log("Listener update");
            Listener updated = os.networking().lbaasV2().listener()
                    .update("9adccd51-44d6-4ddb-8cb5-ab4b112b24ba", Builders.listenerV2Update()
                            .description("updated listener")
                            .connectionLimit(88)
                            .build());
            log(updated);
            waitforit();
        }

        if (runtest){
            log("Listener delete");
            os.networking().lbaasV2().listener().delete("9adccd51-44d6-4ddb-8cb5-ab4b112b24ba");
            waitforit();
        }

    }
}
