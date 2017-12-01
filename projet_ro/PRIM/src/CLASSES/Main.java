package CLASSES;


import java.io.File;


import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;





public class Main {
	
	
	public static Graphe LectureFichier(String file_absolute_path)
	{
		
		Graphe graph=null;
		
		ArrayList<String> chaine=null;
		
			 try {
				
				Scanner sc = new Scanner(new File(file_absolute_path));
				int type = sc.nextInt();
				int n = sc.nextInt();
				graph = new Graphe(n, type);
				
				for(int i = 0; i<n; i++)
				{
					for(int j = 0; j<n; j++){
						int weight = sc.nextInt();
						
						if((i != j && weight>0)){
							graph.addEdge(i+1, j+1, weight);
						}
						
					}
				}
				sc.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("introuvable");
				System.exit(1);
			}
		
		
			
		return graph;
		
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String path_file="";
		
		if (args.length ==0) {
			 
			    System.out.println("VEUILLEZ METTRE LE CHEMIN ABSOLU DU FICHIER");
			    
			    System.exit(1);
		
		}
		
		
		path_file=args[0];
		

		
		
	Graphe g=LectureFichier(path_file);
		g.PrimeMST(g);
		g.printMST(g.getMSTholder());
		System.out.println("\n Son cout est de :"+g.MSTCost(g.getMSTholder()));
	}
	
}
