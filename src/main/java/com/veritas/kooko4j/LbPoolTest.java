package com.veritas.kooko4j;

import org.openstack4j.api.Builders;
import org.openstack4j.api.OSClient;
import org.openstack4j.model.network.ext.LbMethod;
import org.openstack4j.model.network.ext.LbPoolV2;
import org.openstack4j.model.network.ext.MemberV2;
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
        }


        if (runtest){
            log("Lb Pool delete");
            os.networking().lbaasV2().lbPoolV2()
                    .delete("bbc73b75-0494-46bf-9038-c64c26524e74");
        }

        log("Testing Members");

        boolean membertests = false;

        if (membertests){
            log("Members list");
            List<? extends MemberV2> list = os.networking().lbaasV2().lbPoolV2().listMembers("c245c7ed-9a58-4215-9572-39f767234e0c");
            log(list);
        }

        if (membertests){
            log("Members list w/ param");
            Map<String,String> m = new HashMap<String,String>();
            m.put("name", "member1");
            List<? extends MemberV2> list = os.networking().lbaasV2().lbPoolV2().listMembers("c245c7ed-9a58-4215-9572-39f767234e0c", m);
            log(list);
        }

        if (membertests){
            log("Member create");
            MemberV2 member = os.networking().lbaasV2().lbPoolV2()
                    .createMember("c245c7ed-9a58-4215-9572-39f767234e0c", Builders.memberV2()
                    .address("10.0.0.17")
                    .protocolPort(80)
                    .subnetId("388c5684-86b0-49ab-90ef-944b1f7328f8")
                    .weight(2)
                    .build());
            log(member);
        }

        if (membertests){
            log("Member update");
            MemberV2 updated = os.networking().lbaasV2().lbPoolV2()
                    .updateMember("c245c7ed-9a58-4215-9572-39f767234e0c", "9d4cc7eb-7f93-43ea-b18e-ad60048e3e05",
                    Builders.memberV2Update()
                    .adminStateUp(true)
                    .weight(10)
                    .build());
            log(updated);
        }

        if (membertests){
            log("Member delete");
            os.networking().lbaasV2().lbPoolV2().deleteMember("c245c7ed-9a58-4215-9572-39f767234e0c", "9d4cc7eb-7f93-43ea-b18e-ad60048e3e05");
        }
    }
}
