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

        String endpoint = System.getenv("KO4J_ENDPT");
        if (endpoint == null) {
            System.out.println(op + "ERROR: no auth url provided.");
            System.out.println(op + "Please set env var KO4J_ENDPT");
            return;
        }

        System.out.println(op + "Testing auth url: " + endpoint);

        try {
            OSClient.OSClientV2 os = OSFactory.builderV2()
                    .endpoint(endpoint)
                    .credentials("admin","admin")
                    .authenticate();
            System.out.println(op + "Authentication successful");
        }catch (Exception e){
            System.out.println(op + "Authentication ERROR");
        }

//        Test out call to v2 lbaas loadbalancer
//        System.out.println(op + "v2 loadbalancer");
//        os.networking().loadbalancers()
    }
}
