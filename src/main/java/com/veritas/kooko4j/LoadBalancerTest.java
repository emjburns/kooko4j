package com.veritas.kooko4j;

import org.openstack4j.api.Builders;
import org.openstack4j.api.OSClient;
import org.openstack4j.model.network.ext.LoadBalancerV2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoadBalancerTest extends CommonTest{
    public void runTest(OSClient.OSClientV2 os){

        log("Testing LoadBalancer");

        boolean runtest = false;

        if (runtest){
            log("Loadbalancer list");
            List<? extends LoadBalancerV2> list = os.networking().lbaasV2().loadbalancerV2().list();
            log(list);
        }

        if (runtest){
            log("Loadbalancer list");
            Map<String,String> m = new HashMap<String,String>();
            m.put("name", "lbtest");
            List<? extends LoadBalancerV2> list = os.networking().lbaasV2().loadbalancerV2().list(m);
            log(list);
        }

        if (runtest){
            log("Loadbalancer create");
            LoadBalancerV2 lb1 = os.networking().lbaasV2().loadbalancerV2()
                    .create(Builders.loadbalancerV2()
                            .name("lb1")
                            .description("im a baby lb")
                            .subnetId("388c5684-86b0-49ab-90ef-944b1f7328f8")
                            .build());
            log(lb1);
            waitforit();
        }

        if (runtest){
            log("Loadbalancer update");
            LoadBalancerV2 updated = os.networking().lbaasV2().loadbalancerV2()
                    .update("26c7f34c-a2f8-4c20-8ae6-40b311943b4c", Builders.loadBalancerV2Update()
                    .description("im a real lb now!")
                    .build());
            log(updated);
            waitforit();
        }

        if (runtest){
            log("Loadbalancer delete");
            os.networking().lbaasV2().loadbalancerV2().delete("26c7f34c-a2f8-4c20-8ae6-40b311943b4c");
            waitforit();
        }

    }
}
