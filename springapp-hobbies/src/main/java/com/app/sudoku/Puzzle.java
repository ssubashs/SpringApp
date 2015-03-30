package com.app.sudoku;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.codehaus.jackson.annotate.JsonProperty;

public class Puzzle 
{
	private static final int size=9;
	@JsonProperty
	private String id;
	@JsonProperty
	private Integer[][] grid = new Integer[size][size];
	
//	private Integer[][] grid ={
//							   {1,3,0,4,0,0,6,8,9},
//							   {0,2,4,0,9,0,0,5,0},
//						       {0,7,0,0,1,0,4,0,0},
//							   {0,0,0,0,0,8,0,0,0},
//							   {0,8,5,0,0,0,9,7,4},
//							   {2,0,9,0,6,0,0,0,0},
//							   {0,4,0,0,2,0,0,5,0},
//							   {0,5,0,0,4,9,7,0,0},
//							   {0,0,7,0,0,6,0,0,8}
//							  };
	
	public Puzzle()
	{
		
	}
	public Puzzle(Integer[][] grid)
	{
		this.grid = grid;
	}
	public static Puzzle randomize(int percent)
	{
		Integer[][] temp = new Integer[size][size];
		for(int row=0;row<size;row++)
		{
			for(int column=0;column<size;column++)
			{
				if(percent >= Math.random()*100)
					temp[row][column] =  (int) Math.floor((Math.random()*10));
				else
					temp[row][column] = 0;
			}
		}
		Puzzle toreturn = new Puzzle();
		toreturn.setId(UUID.randomUUID().toString());
		toreturn.setGrid(temp);
		return toreturn;
	}

	
	public Integer[][] getGrid() {
		return grid;
	}

	public void setGrid(Integer[][] grid) {
		this.grid = grid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	} 
	
	public Integer getbyIndex(int xy)
	{
		if(xy != 0 && xy < this.size*this.size)
		{
			System.out.println("x : "+  (xy/this.size) + " , y: "+xy%this.size);
			return this.grid[xy/this.size][xy%this.size];
		}
		else
			return null;
	}
	
	// row < 9 and column <9
	public Integer getIndex(int row, int column)
	{
		if(row < this.size &&  column < this.size)
		{
			return (row*this.size+column);
		}
		else
			return null;
		
	}
	
	public void solve()
	{
		
	}
	
	public Clue getClue(int row, int col)	
	{
		if(this.grid[row][col] == null || this.grid[row][col] != 0)
		{
			HashSet<Integer> cluesset = new HashSet<Integer>();
			cluesset.add(0);
			Clue clue = new Clue(this.getIndex(row, col));
			clue.setOptions(cluesset);
			return clue;
		}
		HashSet<Integer> cluesset = new HashSet<Integer>(Arrays.asList(new Integer[]{1,2,3,4,5,6,7,8,9}));
		// row 
		for(int j =0; j < this.size;j++)			
			cluesset.remove(this.grid[row][j]);
		// column
		for(int i=0;i<this.size;i++)
			cluesset.remove(this.grid[i][col]);
		
		// subgrid
		int subrow = row/3;
		int subcol = col/3;
		
		for(int i=subrow*3; i<subrow+3;i++){
			for(int j=subcol*3;j<subcol+3;j++){
				cluesset.remove(this.grid[i][j]);
			}
		}
		Clue clue = new Clue(this.getIndex(row, col));
		clue.setOptions(cluesset);
		return clue;
	}
	
	public class Clue{
		
		Integer index;
		Set<Integer> options;
		
		
		
		public Clue(Integer index) {
			super();
			this.index = index;
			
		}
		
		
		public int getIndex() {
			return index;
		}
		public void setIndex(int index) {
			this.index = index;
		}
		public Set<Integer> getOptions() {
			return options;
		}
		public void setOptions(Set<Integer> options) {
			this.options = options;
		}


		@Override
		public String toString() {
			return "Clue [index=" + index + ", options=" + options + "]";
		}
		
		
		
	}
	
	
}
