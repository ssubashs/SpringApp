package com.datamodel;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TreeMap;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import com.app.sudoku.Puzzle;
import com.app.sudoku.SudokuInmemoryService;

public class Basic {
	
	private Puzzle puzzle;
	
	@Before
	public void init()
	{
		String jsonPuzzle = "{\"id\":\"50fd040b-3ac0-4c3f-9b76-b1e09ea12fca\",\"grid\":[[5,8,0,0,0,9,1,7,0],[9,7,0,0,0,5,0,0,2],[0,2,0,0,6,0,0,5,4],[0,0,0,0,5,0,0,8,0],[0,0,3,1,4,8,7,0,0],[0,9,0,0,3,0,0,0,0],[6,5,0,0,8,0,0,1,0],[4,0,0,5,0,0,0,9,8],[0,1,8,6,0,0,0,2,3]]}";
		try {
			this.puzzle = new ObjectMapper().readValue(jsonPuzzle, Puzzle.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testLinkedHashmap()
	{
		System.out.println("puzzle is "+SudokuInmemoryService.toString(this.puzzle));
		
		for(int i=0;i<9;i++)
		{
			for(int j=0;j<9;j++)
			{
				System.out.println(this.puzzle.getClue(i, j).toString());
			}
		}
		
//		LinkedHashMap<Integer, List<Integer>> sudokumap = new LinkedHashMap<Integer,List<Integer>>();
//		System.out.println(puzzle.getbyIndex(1));
//		System.out.println(puzzle.getbyIndex(5));
//		System.out.println(puzzle.getbyIndex(7));
//		System.out.println(puzzle.getbyIndex(18));
//		System.out.println(puzzle.getbyIndex(26));
//		System.out.println(puzzle.getbyIndex(35));
		
		
	}
	
	@Test
	public void testTreemap()
	{
		TreeMap<Integer, String> clues = new TreeMap<Integer, String>();
		clues.put(2, "two");
		clues.put(3, "three");
		clues.put(1, "one");
		System.out.println(clues.firstEntry());
		
	}
	
	public void testSolve()
	{
		
	}

}
