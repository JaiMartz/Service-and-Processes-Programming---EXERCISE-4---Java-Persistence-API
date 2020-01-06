/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistenciadatos;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import modelos.Libro;

/**
 *
 * @author Jairo
 */
public class PersistenciaDatos {
    
        //Atributos globales
        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion;
        
    public static void main(String[] args) {
        
        PersistenciaDatos programa = new PersistenciaDatos();
        programa.iniciar();
    }
    
    //Iniciamos el programa
    public void iniciar(){
        System.out.println("********************************************************\n" +
            "\n" +
            "* PSP - Tarea Individual 5 - Biblioteca casera         *\n" +
            "\n" +
            "********************************************************\n" +
            "\n" +
            "* JAIRO MARTÍNEZ GARRIDO                               *\n" +
            "\n" +
            "********************************************************\n" +
            "\n" +
            "* DNI 76652856C                                        *\n" +
            "\n" +
            "******************************************************** ");
                //Crea un objeto EntityManagerFactory a partir de una base de datos libros.odb
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("libros.odb");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        try{
        //Menu por consola
        while(!salir){
            System.out.println("Elija la la orden a ejecutar.");
            System.out.println("0. -Insertar lista(Solo introducir en la primera ejecución.)");
            System.out.println("1. -Listar los libros almacenados en la biblioteca");
            System.out.println("2. -Consultar un libro usando el número de referencia");
            System.out.println("3. -Modificar los datos de un libro");
            System.out.println("4. -Eliminar un libro de la biblioteca");
            System.out.println("5. -Salir");
            System.out.println("");
            System.out.println("Introduza el numero de opcion elegida.");
            opcion = sn.nextInt();
            
            switch(opcion){
                case 0:
                    this.insertar(entityManager);
                    break;
                case 1:
                    this.listar(entityManager);
                    break;
                case 2:
                    this.consultar(sn,entityManager);
                    break;
                case 3:
                    this.modificar(sn,entityManager);
                    break;
                case 4:
                    this.eliminar(sn,entityManager);
                    break;
                case 5:
                    System.out.println("Se ha salido de la aplicación");
                    salir = true;
                    break;
                }
            }
        //Cerramos los flujos
        entityManager.close();
        entityManagerFactory.close();
        }catch(InputMismatchException e){
                System.out.println("El comando introducido debe estar entre los valores 1 - 5");
                sn.next();
        }
    }
    /**Lista los libros de la base de datos
     * 
     * @param entityManager 
     */
    public void listar(EntityManager entityManager){
        
        TypedQuery<Libro> consulta = (TypedQuery<Libro>) entityManager.createQuery("SELECT libro FROM Libro libro");
        List<Libro> resultado = consulta.getResultList();
        for(Libro libro: resultado){
            System.out.println(libro);
        }
        System.out.println("");
    }
    
    /**Consultamos un libro por su numero de referencia
     * 
     * @param sn
     * @param entityManager 
     */
    public void consultar(Scanner sn, EntityManager entityManager){
        opcion = 0;
        System.out.println("Introduzca el numero de referencia del libro");
        opcion = sn.nextInt();
        
        
        TypedQuery<Libro> consulta =(TypedQuery<Libro>) entityManager.createQuery("SELECT libro FROM Libro libro");
        List<Libro> resultado = consulta.getResultList();
        
         for(Libro libro: resultado){
            if(libro.getNumeroRef()==opcion){
                System.out.println(libro);
                System.out.println("");
            }else{
                System.out.println("El codigo introducido no pertenece a ningun libro.");
            }           
         }
            
    }
    /**Modificamos un objeto Libro
     * 
     * @param sc
     * @param entityManager 
     */
    public void modificar(Scanner sc, EntityManager entityManager){
        String lector = null;
        Double puntuacion;
        opcion = 0;
    //Busqueda del libro
        System.out.println("Introduzca el numero de referencia del libro que desea modificar");
        opcion= sn.nextInt();
        
        entityManager.getTransaction().begin();
        TypedQuery<Libro> consulta =(TypedQuery<Libro>) entityManager.createQuery("SELECT libro FROM Libro libro");
        List<Libro> resultado = consulta.getResultList();
        
        try{
         for(Libro libro: resultado){
            if(libro.getNumeroRef()==opcion){
                System.out.println("\nIntroduzca nuevo valor para Nombre");
                lector=sn.nextLine();
                libro.setNombre(lector);

                System.out.println("\nIntroduzca nuevo valor para Autor");
                lector=sn.nextLine();
                libro.setAutor(lector);

                System.out.println("\nIntroduzca nuevo valor para Genero");
                lector=sn.nextLine();
                libro.setGenero(lector);

                System.out.println("\nIntroduzca nuevo valor para Leido");  
                lector=sn.nextLine();
                libro.setLeido(lector);

                System.out.println("\nIntroduzca nuevo valor para Puntuacion");
                puntuacion=sn.nextDouble();
                libro.setPuntuacion(puntuacion);

                System.out.println("\nIntroduzca nuevo valor para Comentarios"); 
                lector=sn.nextLine();
                libro.setComentarios(lector);
                }
            }
            entityManager.getTransaction().commit();
                }catch(InputMismatchException e){
                System.out.println("La puntuacion introducida debe estar entre los valores 1 - 10");
                sn.next();
            }
        }
        
    /**Elimiamos un objeto Libro de la base de datos
     * 
     * @param sn
     * @param entityManager 
     */
    public void eliminar(Scanner sn, EntityManager entityManager){
        try{
        System.out.println("Introduzca el numero de referencia del libro que desea modificar");
        opcion = sn.nextInt();
        
        entityManager.getTransaction().begin();
        TypedQuery<Libro> consulta =(TypedQuery<Libro>) entityManager.createQuery("SELECT libro FROM Libro libro");
        List<Libro> resultado = consulta.getResultList();
        
         for(Libro libro: resultado){
            if(libro.getNumeroRef()==opcion){
                entityManager.remove(libro);
            }
         }
        System.out.println("El libro ha sido eliminado.");
        entityManager.getTransaction().commit();
        }catch(InputMismatchException e){
                System.out.println("El valor introducido debe ser un numero");
                sn.next();
        }
    }
    /**Insertamos varios objetos Libro en la base de datos
     * 
     * @param entityManager 
     */
    public void insertar(EntityManager entityManager){
        entityManager.getTransaction().begin();
        entityManager.persist(new Libro("Cien años de soledad","Gabriel García Márquez", "Drama","Si",8.5,"Libro profundo"));
        entityManager.persist(new Libro("El elfo oscuro","Robert Anthony Salvatore", "Fantasia","Si",9.7,"Trilogia entretenida"));
        entityManager.persist(new Libro("La rueda del tiempo","Andrew Timberly", "Policiaca","No",0,"Pendiente de lectura"));
        entityManager.getTransaction().commit();
        System.out.println("Los libros se han insertado en la base de datos.");

    }
}
