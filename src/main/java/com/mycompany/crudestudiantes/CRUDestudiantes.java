

package com.mycompany.crudestudiantes;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;



public class CRUDestudiantes {

    public static void main(String[] args) {
        MongoClient mongo = crearConexion();
        
        if ( mongo != null){
            DB db= mongo.getDB("Pruebas");
            
            
            System.out.println("BASE DE DATOS CREADA");
     
            //CREA UNA COLECCION SI NO EXISTE 
            insertarEstudiantes(db, "usuarios", "Pedro", "Primero Básico");
            insertarEstudiantes(db, "usuarios", "Jose", "Quinto Básico");
            insertarEstudiantes(db, "usuarios", "Esteban", "Sexto Básico");
            
            
             mostrarColeccion(db, "usuarios");
        }
        
    }
    
    public static MongoClient crearConexion(){
        System.out.println("PRUEBA CONEXION MONGODB");
        
        MongoClient mongo = null;
        
        mongo = new MongoClient("localhost", 27017);
        
        return mongo;
    
    }
    
    //INSERTAR DATOS A LA COLECCION USUARIOS
    public static void insertarEstudiantes(DB db, String coleccion, String nombre, String curso ){
        DBCollection regsitroEstudiantes = db.getCollection(coleccion);
        
        BasicDBObject documento =  new BasicDBObject();
        documento.put("nombre", nombre);
        documento.put("curso", curso);
        
        regsitroEstudiantes.insert (documento);
    
    }
    
    //MOSTRAR LOS REGISTROS DE LA COLECCION USUARIOS
    public static void mostrarColeccion(DB db, String coleccion){
        DBCollection regsitroEstudiantes = db.getCollection(coleccion);
        DBCursor cursor = regsitroEstudiantes.find();
        
        while(cursor.hasNext()){
            System.out.println("*" + cursor.next().get("nombre") + " - " + cursor.curr().get("curso"));
        }
        
    }
    

     
}