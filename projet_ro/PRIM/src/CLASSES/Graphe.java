package CLASSES;

import java.util.Iterator;



import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Graphe {
	

		 // nombre de sommets du graphe
		 int numVertices;
		 public static final int INF=98000000;
		 int type;// s'il vaut 1 alors le graphe est non-orienté; mais s'il vaut 2 alors le graphe est orienté
		 // listes d'adjacence des sommets
		 Map<Integer,LinkedList<ListAdjency>> adjList;
		 Map<Integer,Integer> MSTholder;
		 
		 // contient les noeuds qui ne sont pas encore inclus dans l'arbre couvrant de poids minimal
		 TreeMap<Integer,Integer> heap;

		 public Graphe(int numVertices,int i) {
		  this.numVertices=numVertices;
		  this.adjList=new TreeMap<Integer,LinkedList<ListAdjency>>();
		   heap=null;
		  MSTholder=null;
		  type=i;
		 }

		 public void addEdge( int src, int dest, int weight) {
				ListAdjency node=new ListAdjency(dest, weight);
				  LinkedList<ListAdjency> list=null;

				  // ajoute le noeud adjacent a chaque noeud source
				  list= adjList.get(src);
				  if(list ==null)
				   list=new LinkedList<ListAdjency>();
				  
				  list.addLast(node);
				  adjList.put(src, list);
				 
				  if(type==2){
				  //ajoute le noeud adjacent a chaque noeud destination , dans le cas d'un graphe orienté
					 node=new ListAdjency(src, weight);
					  
					  list=null;
					  
					  list=adjList.get(dest);
					  if(list ==null)
					   list=new LinkedList<ListAdjency>();

					  list.addFirst(node);

					  adjList.put(dest, list);
				 
				  }
		 }
		 
		 public TreeMap<Integer, Integer> getHeap() {
			return heap;
		}

		public void setHeap(TreeMap<Integer, Integer> heap) {
			this.heap = heap;
		}

		public int getNumVertices() {
			return numVertices;
		}

		public void setNumVertices(int numVertices) {
			this.numVertices = numVertices;
		}

		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
		}

		public Map<Integer, LinkedList<ListAdjency>> getAdjList() {
			return adjList;
		}

		public void setAdjList(Map<Integer, LinkedList<ListAdjency>> adjList) {
			this.adjList = adjList;
		}

		public Map<Integer, Integer> getMSTholder() {
			return MSTholder;
		}

		public void setMSTholder(Map<Integer, Integer> mSTholder) {
			MSTholder = mSTholder;
		}

		public void createHeap(int vertex, Integer weight) {
			  if(heap == null)
			   heap=new TreeMap<Integer,Integer>();

			  heap.put(vertex,weight);
			 }
		 
		 private int findMin() {
			  Set<Map.Entry<Integer, Integer>>list=heap.entrySet();

			  int minKey=heap.firstKey();
			  int minValue =INF;
			  if(list !=null)
			  {
			   for(Map.Entry<Integer, Integer> entry: list)
			   {
			    if(minValue > entry.getValue())
			    {
			     minValue=entry.getValue();
			     minKey=entry.getKey();
			    }
			   }
			  }
			  return minKey;
			 }

		 
		 
		 
		 
		 
		 public void PrimeMST(Graphe graph) {
			  // liste contenant l'arbre couvrant de poids minimal
			  MSTholder=new TreeMap<Integer,Integer>();

			  // initialise le tas
			  Set<Integer> set=adjList.keySet();

			  // Creation  de toutes les clés de la liste d'adjacence
			  for(Integer i:set)
			  {
			   createHeap(i,INF);
			  }
			 
			 
			  while(heap.size()!=0)
			  {
			   int minEdgeVertex=findMin(); // retourne l'arete de petit poids du graphe
			   
			   heap.remove(minEdgeVertex);  // retire le sommet du graphe deja traité

			   LinkedList<ListAdjency> list=adjList.get(minEdgeVertex);
			   Iterator<ListAdjency> it=list.iterator();
			   
			   while(it.hasNext())
			   {
				   ListAdjency node=it.next();
			    int vertex=node.dest;
			  
			    if( (heap.containsKey(vertex)) && (node.weight < heap.get(vertex)))
			    {
			    	
			     heap.put(vertex,node.weight); // decreasing node value
			     
			     MSTholder.put(vertex, minEdgeVertex);
			     
			    }
			   }
			  
			  }
			
			
}
		 
		 // calcule le cout de l'arbre couvrant de poid minimal
		 public long MSTCost(Map<Integer,Integer> MSTholder) {
			  Set<Map.Entry<Integer,Integer>> set=MSTholder.entrySet();

			  long sum=0;
			  for (Map.Entry<Integer, Integer> entry :set)
			  {

			   int cle=entry.getKey();
			   int valeur=entry.getValue();
			   
			   List<ListAdjency > list=adjList.get(valeur);
			   if(list!=null)
			   {
			    for(int j=0;j<list.size();j++)
			    {
			    	ListAdjency  node=list.get(j);
			     if((node.dest) == cle )
			     {
			      sum+=node.weight;
			     
			     }
			    }
			   }
			  }
			  return sum;
			 }	 

		 
		 public void printMST(Map<Integer,Integer> MSTholder) 
		 {

			 int vertex=0;
			  System.out.println("L'arbre couvrant de poids minimal est : "+"\n");
			  Set<Map.Entry<Integer,Integer>> set=MSTholder.entrySet();

			  for (Map.Entry<Integer, Integer> entry :set)
			  {
				  LinkedList<ListAdjency> list = adjList.get(entry.getKey());
				  Iterator<ListAdjency> it=list.iterator();
				   
				   while(it.hasNext())
				   {
					   ListAdjency node=it.next();
					   vertex=node.dest;
					   if(vertex==entry.getValue()){
						   vertex=node.getWeight();
					   break;}
					 }  
				 
				 
			   System.out.println(entry.getKey()   + " -- " + entry.getValue()+"--le poids de cette arête est : " + vertex +"\n");
			   }
		 }
			 

		 
}
