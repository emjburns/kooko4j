package com.veritas.kooko4j;

import org.openstack4j.api.Builders;
import org.openstack4j.api.OSClient;
import org.openstack4j.model.network.ext.LbMethod;
import org.openstack4j.model.network.ext.LbPoolV2;
import org.openstack4j.model.network.ext.Protocol;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LbPoolTest extends CommonTest{
    public void runTest(OSClient.OSClientV2 os){
        log("Testing LbPool");

        boolean runtest = false;

        if (runtest){
            log("Lb Pool list");
            List<? extends LbPoolV2> list = os.networking().lbaasV2().lbPoolV2().list();
            log(list);
        }

        if (runtest){
            log("Lb Pool list w/ param");
            Map<String,String> m = new HashMap<String,String>();
            m.put("name", "pool1");
            List<? extends LbPoolV2> filteredlist = os.networking().lbaasV2().lbPoolV2().list(m);
            log(filteredlist);
        }

        if (runtest){
            log("Lb Pool create");
            LbPoolV2 lbPoolV2 = os.networking().lbaasV2().lbPoolV2()
                    .create(Builders.lbpoolV2()
                            .name("testlbpool44")
                            .description("this is a lb pool")
                            .protocol(Protocol.HTTP)
                            .lbMethod(LbMethod.ROUND_ROBIN)
                            .listenerId("37e23991-e2b3-4929-93d9-dc92e450fc31")
                            .build());
            log(lbPoolV2);
            waitforit();
        }


        if (runtest){
            log("Lb Pool update");
            LbPoolV2 updated = os.networking().lbaasV2().lbPoolV2()
                    .update("b7f6a49f-ebd8-43c5-b792-5748366eff21", Builders.lbPoolV2Update()
                            .adminStateUp(true)
                            .lbMethod(LbMethod.LEAST_CONNECTIONS)
                            .description("im a swimming pool")
                            .build());
            log(updated);
            waitforit();
        }


        if (runtest){

            log("Lb Pool delete");
            os.networking().lbaasV2().lbPoolV2()
                    .delete("bbc73b75-0494-46bf-9038-c64c26524e74");
            waitforit();
        }
    }
}
