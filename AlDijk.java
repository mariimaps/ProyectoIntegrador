/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete.clases;

import java.util.Scanner;

/**
 *
 * @author Maritza
 */

public class AlDijk {
    //Se crea la tablaMod que es la tabla de rutas que se mostrará como resultado
    public static int[][] CrearTablaMod(int n, int RInicio){
         int tablaN[][]=new int[n][4];
        
        for(int i=0;i<n;i++){
            tablaN[i][0]=i;
            tablaN[i][1]=1000;
            tablaN[i][2]=-1;        
            tablaN[i][3]=0;
        }
        tablaN[RInicio][1]=0; //La distancia del router de inicio siempre será 0
        return tablaN;
    }
    public static void dijkstra(int[][] tablaMod, int n, int[][] tablaEnr){
        //Se piden como parámteros iniciales la tablaMod, la longitud de la matriz Enr y la matriz tablaEnr
        //En el main se utilizará esta función por cada router 
        int pos=HallarMenor(tablaMod,n); //Se llama a la función HallarMenor que retorna pos 
        tablaMod[pos][3]=1;
        for(int i=0;i<n;i++){
            if(tablaEnr[pos][i]!=1000)
            {                    
                if(tablaEnr[pos][i]+tablaMod[pos][1]<tablaMod[i][1])
                {
                    tablaMod[i][1]= tablaEnr[pos][i] + tablaMod[pos][1];
                    tablaMod[i][2]=pos;                                            
                }
            }
        }
    }
    
    public static int HallarMenor(int[][] tablaMod, int n){
        int menor=1000;
        int pos=0;
        for(int i=0; i<n; i++)
        {
            if(tablaMod[i][3]!=1 && tablaMod[i][1]<= menor)
            {
              menor=tablaMod[i][1];
              pos=i; //pos es la posición del router con menor distancia según la tabla mod 
            }  
        }
        return pos;
    }
    
    public static void IngresarArista(int[][] tablaEnr){
        //Se ingresan y almacenan los valores en la matriz de enrutamiento
        Scanner sc = new Scanner(System.in);
        System.out.println("Router incial: ");
        int RI = sc.nextInt();      
        System.out.println("Router final: ");
        int RF = sc.nextInt();  
        System.out.println("Peso: ");
        int dist=sc.nextInt();  
        tablaEnr[RI][RF]=dist;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Cantidad de routers en la red: ");
        int n=sc.nextInt();   
        int tablaEnr[][]=new int[n][n];
        System.out.println("Router inicio: ");
        int RInicio=sc.nextInt();
     
        for (int i=0; i<n; i++)
        {
            for (int j=0; j<n; j++)
            {
                tablaEnr[i][j]=1000;
            }
            
        }
        System.out.println("Cantidad de enlaces: ");
        
     
        
        int cant=sc.nextInt(); 
        
        for(int i=0;i<cant;i++)
        {
            IngresarArista(tablaEnr);
        }
        System.out.print("\n");
        
        long inicio = System.currentTimeMillis();
       
        int[][] tablaN=CrearTablaMod(n, RInicio);
        for(int i=0;i<n;i++){
            dijkstra(tablaN, n, tablaEnr);
        }
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("Nro Router \t Dist de Origen \t  Router Ant");
        for (int i=0; i < tablaN.length; i++) {
            System.out.println("\n");
            for (int j=0; j<3; j++) 
            {
                System.out.print (tablaN[i][j]+"\t\t\t");
              
            }
        }
        long fin=System.currentTimeMillis();
        double tiempo = (double)(fin - inicio);
        System.out.println("\n-----------Tiempo de ejecución------------------");
        System.out.println(tiempo +" milisegundos");
    }
    
}
