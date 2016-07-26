package com.veritas.kooko4j;


import org.openstack4j.api.Builders;
import org.openstack4j.api.OSClient;
import org.openstack4j.model.network.ext.HealthMonitorType;
import org.openstack4j.model.network.ext.HealthMonitorV2;
import org.openstack4j.model.network.ext.LbMethod;
import org.openstack4j.model.network.ext.LbPoolV2;
import org.openstack4j.model.network.ext.Listener;
import org.openstack4j.model.network.ext.LoadBalancerV2;
import org.openstack4j.model.network.ext.Protocol;

public class CreateFlowTest extends CommonTest{
    public void runTest(OSClient.OSClientV2 os){
        boolean runtest = false;
        int n = 10;
        log("Create flow");

        if (runtest){
            log("Loadbalancer create");
            LoadBalancerV2 lb1 = os.networking().lbaasV2().loadbalancerV2()
                    .create(Builders.loadbalancerV2()
                            .name("lb" + n)
                            .description("im a baby lb")
                            .subnetId("388c5684-86b0-49ab-90ef-944b1f7328f8")
                            .build());
            log(lb1);
            waitforit();
        }

        if (runtest){
            log("Listener create");
            Listener listener = os.networking().lbaasV2().listener()
                    .create(Builders.listenerV2()
                            .name("listener" + n)
                            .description("listener here")
                            .protocol(Protocol.HTTP)
                            .protocolPort(80)
                            .loadBalancerId("d8b09924-d223-42a8-b7e7-410e60fd04c5")
                            .build());
            log(listener);
            waitforit();
        }

        if (runtest){
            log("Lb Pool create");
            LbPoolV2 lbPoolV2 = os.networking().lbaasV2().lbPoolV2()
                    .create(Builders.lbpoolV2()
                            .name("testlbpool" + n)
                            .description("this is a lb pool")
                            .protocol(Protocol.HTTP)
                            .lbMethod(LbMethod.ROUND_ROBIN)
                            .listenerId("4e1dc45b-31b1-480d-850c-8ee86dd3713d")
                            .build());
            log(lbPoolV2);
            waitforit();
        }

        if (runtest){
            log("Healthmonitor create");
            HealthMonitorV2 hm = os.networking().lbaasV2().healthMonitorV2()
                    .create(Builders.healthmonitorV2()
                            .delay(3)
                            .maxRetries(6)
                            .timeout(2)
                            .type(HealthMonitorType.HTTP)
                            .poolId("c245c7ed-9a58-4215-9572-39f767234e0c")
                            .build());
            log(hm);
            waitforit();

        }
    }
}
