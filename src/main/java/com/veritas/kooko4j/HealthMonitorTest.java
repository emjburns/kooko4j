package com.veritas.kooko4j;

import org.openstack4j.api.Builders;
import org.openstack4j.api.OSClient;
import org.openstack4j.model.network.ext.HealthMonitorType;
import org.openstack4j.model.network.ext.HealthMonitorV2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HealthMonitorTest extends CommonTest{
    public void runTest(OSClient.OSClientV2 os){
        log("Testing Healthmonitor");

        boolean runtest = false;

        if (runtest){
            log("Healthmonitor list");
            List<? extends HealthMonitorV2> list = os.networking().lbaasV2().healthMonitorV2().list();
            log(list);
        }

        if (runtest){
            log("Healthmonitor list");
            Map<String,String> m = new HashMap<String,String>();
            m.put("id", "350576d8-5015-4d4e-b73f-23df2397e4c4");
            List<? extends HealthMonitorV2> list = os.networking().lbaasV2().healthMonitorV2().list(m);
            log(list);
        }

        if (runtest){
            log("Healthmonitor create");
            HealthMonitorV2 hm = os.networking().lbaasV2().healthMonitorV2()
                    .create(Builders.healthmonitorV2()
                            .delay(3)
                            .maxRetries(6)
                            .timeout(2)
                            .type(HealthMonitorType.HTTP)
                            .poolId("7552cd8f-b854-47f2-9674-88975a5ae3fa")
                            .build());
            log(hm);
            waitforit();
        }

       if (runtest){
            log("Healthmonitor update");
            HealthMonitorV2 updated = os.networking().lbaasV2().healthMonitorV2()
                    .update("241ac8fd-0c2e-4c90-8ed6-990cb75f96fd", Builders.healthMonitorV2Update()
                            .timeout(99)
                            .delay(1)
                            .build());
            log(updated);
            waitforit();
        }

        if (runtest){
            log("Healthmonitor delete");
            os.networking().lbaasV2().healthMonitorV2().delete("241ac8fd-0c2e-4c90-8ed6-990cb75f96fd");
            waitforit();
        }

    }
}
