package model.algorithm;

import java.util.*;

public class DijkstraAlgorithmMethod {
	private int N, M;
	private boolean charInHead;
	private final int MAXN = 1001;
	private int arr[][];
	public int parent[];
	public int dist[];
	public Boolean sptSet[];

	public DijkstraAlgorithmMethod() {
		this.charInHead = false;
		arr = new int[MAXN][MAXN];
		parent = new int[MAXN];
		dist = new int[MAXN];
		sptSet = new Boolean[MAXN];
	}
	
	public void dijkstra(int graph[][], int src, int start)
	{
		System.out.println(N);
		
		if(start == 0) {
			for (int i = 0; i < N; i++) {
				dist[i] = Integer.MAX_VALUE;
				sptSet[i] = false;
				parent[i] = -1;
			}
		}
		else if(start == 1) {
			for (int i = 1; i <= N; i++) {
				dist[i] = Integer.MAX_VALUE;
				sptSet[i] = false;
				parent[i] = -1;
			}
		}
		dist[src] = 0;
		parent[src] = src;

		if(start == 0) {
			for (int count = 0; count < N - 1; count++) {
				
				int u = minDistance(dist, sptSet, start);
				
				
				sptSet[u] = true;
				
				for (int v = 0; v < N; v++)
					
					if (!sptSet[v] && graph[u][v] != 0
					&& dist[u] != Integer.MAX_VALUE
					&& dist[u] + graph[u][v] < dist[v]) {
						dist[v] = dist[u] + graph[u][v];
						parent[v] = u;
					}
				
			}
		}
		else if(start == 1) {
			for (int count = 1; count <= N - 1; count++) {
				
				int u = minDistance(dist, sptSet, start);
				
				sptSet[u] = true;
				
				for (int v = 1; v <= N; v++)
					
					if (!sptSet[v] && graph[u][v] != 0
						&& dist[u] != Integer.MAX_VALUE
						&& dist[u] + graph[u][v] < dist[v]) {
							dist[v] = dist[u] + graph[u][v];
							parent[v] = u;
					}
				
			}
		}
		printSolution(dist, parent, src, start);
	}
	
	public int minDistance(int dist[], Boolean sptSet[], int start)
	{
		int min = Integer.MAX_VALUE, min_index = -1;
		if(start == 0) {
			for (int v = 0; v < N; v++) {
				if (sptSet[v] == false && dist[v] <= min) {
					min = dist[v];
					min_index = v;
				}
			}
		}
		else if(start == 1) {
			for (int v = 1; v <= N; v++) {
				if (sptSet[v] == false && dist[v] <= min) {
					min = dist[v];
					min_index = v;
				}
			}
		}
		return min_index;
	}
	
	public void ResetArray() {
			for(int i = 0; i <= N; i++) {
				for(int j = 0; j <= N; j++) {
					arr[i][j] = 0;
				}
			}
			for (int i = 0; i <= N; i++) {
				dist[i] = Integer.MAX_VALUE;
				sptSet[i] = false;
				parent[i] = -1;
			}
	}

	public boolean isCharInHead() {
		return charInHead;
	}

	public void setCharInHead(boolean charInHead) {
		this.charInHead = charInHead;
	}

	public int[][] ReturnArray() {
		return arr;
	}

	public void setDinh(int N) {
		this.N = N;
	}
	
	public void setCanh(int M) {
		this.M = M;
	}
	
	public int getDinh() {
		return this.N;
	}
	
	public int getCanh() {
		return this.M;
	}
	
	public void printSolution(int dist[], int parent[], int src, int start)
	{
		if(start == 0) {
			System.out.println(
					"Vertex \t\t Distance from Source");
			for (int i = 0; i < N; i++) {
				System.out.println(i + " \t\t " + dist[i]);
				ArrayList<Integer> list = new ArrayList<Integer>();
				int t = i;
				while(src != t) {
					list.add(t);
					if(parent[t] != -1) {
						t = parent[t];
					}
					else {
						break;
					}
				}
				list.add(src);
				System.out.print("Path: ");
				for (int j = list.size() - 1; j >= 0; j--) {
					System.out.print(list.get(j) + " ");
				}
				System.out.println();
			}
		}
		else if(start == 1) {
			System.out.println(
					"Vertex \t\t Distance from Source");
			for (int i = 1; i <= N; i++) {
				System.out.println(i + " \t\t " + dist[i]);
				ArrayList<Integer> list = new ArrayList<Integer>();
				int t = i;
				while(src != t) {
					list.add(t);
					if(parent[t] != -1) {
						t = parent[t];
					}
					else {
						break;
					}
				}
				list.add(src);
				System.out.print("Path: ");
				for (int j = list.size() - 1; j >= 0; j--) {
					System.out.print(list.get(j) + " ");
				}
				System.out.println();
			}
		}
	}
}