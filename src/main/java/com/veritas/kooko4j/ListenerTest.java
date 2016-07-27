package com.veritas.kooko4j;

import org.openstack4j.api.Builders;
import org.openstack4j.api.OSClient;
import org.openstack4j.model.network.ext.ListenerV2;
import org.openstack4j.model.network.ext.Protocol;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListenerTest extends CommonTest{
    public void runTest(OSClient.OSClientV2 os){

        log("Testing Listener");

        boolean runtest = false;

        if (true){
            log("listener list");
            List<? extends ListenerV2> list = os.networking().lbaasV2().listenerV2().list(); 
            log(list);
        }

        if (runtest){
            log("Listener list");
            Map<String,String> m = new HashMap<String,String>();
            m.put("name", "listener1");
            List<? extends ListenerV2> list = os.networking().lbaasV2().listenerV2().list(m);
            log(list);
        }

        if (runtest){
            log("listener get");
            ListenerV2 l = os.networking().lbaasV2().listenerV2().get("c07058a9-8d84-4443-b8f5-508d0facfe10");
            log(l);

        }

        if (runtest){
            log("Listener create");
            ListenerV2 listener = os.networking().lbaasV2().listenerV2()
                    .create(Builders.listenerV2()
                            .name("listener44")
                            .description("listener here")
                            .protocol(Protocol.HTTP)
                            .protocolPort(80)
                            .loadBalancerId("40c5fc75-3df7-4e3f-abe4-41ded1ab387c")
                            .build());
            log(listener);
        }

        if (runtest){
            log("Listener update");
            ListenerV2 updated = os.networking().lbaasV2().listenerV2()
                    .update("9adccd51-44d6-4ddb-8cb5-ab4b112b24ba", Builders.listenerV2Update()
                            .description("updated listener")
                            .connectionLimit(88)
                            .build());
            log(updated);
        }

        if (runtest){
            log("Listener delete");
            os.networking().lbaasV2().listenerV2().delete("9adccd51-44d6-4ddb-8cb5-ab4b112b24ba");
        }

    }
}
