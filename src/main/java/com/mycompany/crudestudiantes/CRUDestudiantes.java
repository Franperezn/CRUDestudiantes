

package com.mycompany.crudestudiantes;

import com.mongodb.MongoClient;


public class CRUDestudiantes {

    public static void main(String[] args) {
        
    MongoClient mongo = crearConexion();


    }
    
    public static MongoClient crearConexion(){
        System.out.println("PRUEBA CONEXION MONGODB");
        
        MongoClient mongo = null;
        
        mongo = new MongoClient("localhost", 27017);
        
        return mongo;
    
    }
    
    
    
}
