package com.veritas.kooko4j;

import org.openstack4j.api.OSClient;
import org.openstack4j.openstack.OSFactory;

/**
 * Program for testing openstack4j work
 */
public class App
        {
    public static void main( String[] args )
    {
        String op = "   >> ";

        System.out.println( op + "Test of lbaas V2 Networking" );

        String auth_endpt = System.getenv("KO4J_ENDPT");
        if (auth_endpt == null) {
            System.out.println(op + "ERROR: no auth url provided.");
            System.out.println(op + "Please set env var KO4J_ENDPT");
            return;
        }

        System.out.println(op + "Testing auth url: " + auth_endpt);

        OSClient.OSClientV2 os;

        try {
            OSFactory.enableHttpLoggingFilter(true);
            os = OSFactory.builderV2()
                    .endpoint(auth_endpt)
                    .credentials("admin","admin")
                    .tenantName("demo")
                    .authenticate();


            System.out.println(op + "Authentication successful");
//            System.out.println(op + "Endpoints:");
//            List<? extends Endpoint> endpoints = os.identity().listTokenEndpoints();
//            System.out.println(op + endpoints);
        }catch (Exception e){
            System.out.println(op + "Authentication ERROR");
            return;
        }

        CreateFlowTest createFlowTest = new CreateFlowTest();
        createFlowTest.runTest(os);

        LbPoolTest lbPoolTest = new LbPoolTest();
        lbPoolTest.runTest(os);

        LoadBalancerTest loadBalancerTest = new LoadBalancerTest();
        loadBalancerTest.runTest(os);

        ListenerTest listenerTest = new ListenerTest();
        listenerTest.runTest(os);

        HealthMonitorTest healthMonitorTest = new HealthMonitorTest();
        healthMonitorTest.runTest(os);

        MemberTest memberTest = new MemberTest();
        memberTest.runTest(os);
    }
}
