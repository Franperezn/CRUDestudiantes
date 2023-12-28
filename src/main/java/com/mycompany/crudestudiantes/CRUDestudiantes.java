

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
            /*
            insertarEstudiantes(db, "usuarios", "Pedro", "Primero Básico");
            insertarEstudiantes(db, "usuarios", "Jose", "Quinto Básico");
            insertarEstudiantes(db, "usuarios", "Esteban", "Sexto Básico");
            
            
             mostrarColeccion(db, "usuarios");
            */
             
             /*
            buscarPorNombre(db, "usuarios", "Pedro");
*/
             //UPDATE
            /*
            System.out.println("ANTES DEL UPDATE");
            mostrarColeccion(db, "usuarios");
            actualizarDocumento(db, "usuarios", "Pedro");
            System.out.println("DESPUES DEL UPDATE");
            mostrarColeccion(db, "usuarios");
            */
            
                        //DELETE
            
            System.out.println("ANTES DEL DELETE");
            mostrarColeccion(db, "usuarios");
            borrarDocumento(db, "usuarios", "Jose");
            System.out.println("DESPUES DEL DELETE");
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
    
        //CONSULTA
    public static void buscarPorNombre (DB db, String coleccion, String nombre){
        DBCollection regsitroEstudiantes = db.getCollection(coleccion);
        
        //CONSULTA POR NOMBRE
        BasicDBObject consulta =  new BasicDBObject();
        consulta.put("nombre", nombre);
        
        
        DBCursor cursor = regsitroEstudiantes.find(consulta);
        while(cursor.hasNext()){
           
            System.out.println("-- " + cursor.next().get("nombre") + " - " + cursor.curr().get("curso"));
            
        }
    }
    
    
     //UPDATE 
    
    public static void actualizarDocumento(DB db, String coleccion, String nombre){
        DBCollection regsitroEstudiantes = db.getCollection(coleccion);
        
        //REEMPLAZAR INFO
        
        BasicDBObject actualizarCurso =  new BasicDBObject();
        actualizarCurso.append("$set", new BasicDBObject().append("curso", "Quinto Básico"));
        
        //BUSCAR EL DOCUMENTO EN LA COLECCION 
        
        BasicDBObject buscarPorNombre =  new BasicDBObject();
        buscarPorNombre.append("nombre", nombre);
        
        //REALIZA EL UPDATE
        regsitroEstudiantes.update(buscarPorNombre, actualizarCurso);
    }   
    
        //DELETE 
    
    public static void borrarDocumento(DB db, String coleccion, String nombre){
        
        DBCollection regsitroEstudiantes = db.getCollection(coleccion);
        
        //REEMPLAZAR INFO
        
        regsitroEstudiantes.remove(new BasicDBObject().append("nombre", nombre));
    }

     
}