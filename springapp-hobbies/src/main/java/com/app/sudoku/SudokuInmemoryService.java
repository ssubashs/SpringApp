package com.app.sudoku;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class SudokuInmemoryService implements ISudokuPuzzleService {

	HashMap<String,Puzzle> puzzleDB = new HashMap<String, Puzzle>();
	
	public SudokuInmemoryService()
	{
//		Integer[][] grid ={
//				   {1,3,0,4,0,0,6,8,9},
//				   {0,2,4,0,9,0,0,5,0},
//			       {0,7,0,0,1,0,4,0,0},
//				   {0,0,0,0,0,8,0,0,0},
//				   {0,8,5,0,0,0,9,7,4},
//				   {2,0,9,0,6,0,0,0,0},
//				   {0,4,0,0,2,0,0,5,0},
//				   {0,5,0,0,4,9,7,0,0},
//				   {0,0,7,0,0,6,0,0,8}
//				  };
//		Puzzle temp = new Puzzle();
//		temp.setGrid(grid);
		ObjectMapper mapper = new ObjectMapper();
		for(int i=0;i<10;i++)
		{
			
			Puzzle temp = Puzzle.randomize(60);		
			
			try {
				System.out.println(" -- >> "+mapper.writeValueAsString(temp));
			} catch (JsonGenerationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			puzzleDB.put(temp.getId(),temp);	
		}
		
	}
	
	@Override
	public Puzzle getPuzzle(String id) {
		// TODO Auto-generated method stub
		return puzzleDB.get(id);
	}

	@Override
	public List<Puzzle> getAllPuzzles() {
		// TODO Auto-generated method stub
		return (List<Puzzle>) puzzleDB.values();
	}

	@Override
	public Puzzle getRandomPuzzle() {
		// TODO Auto-generated method stub
		 Puzzle temp = Puzzle.randomize(60);
		 this.puzzleDB.put(temp.getId(), temp);
		return temp;
	}

	@Override
	public Puzzle getPuzzleClass(String difficulty) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Puzzle savePuzzle(Puzzle newPuzzle) {
		// TODO Auto-generated method stub
		if(newPuzzle.getId() == null || "new".equalsIgnoreCase(newPuzzle.getId()));
			newPuzzle.setId(UUID.randomUUID().toString());
		 puzzleDB.put(newPuzzle.getId(), newPuzzle);
		 return newPuzzle;
	}

	@Override
	public Puzzle getPuzzleRandom(int percent) {
		Puzzle temp =  Puzzle.randomize(percent);
		this.puzzleDB.put(temp.getId(), temp);
		return temp;
	}
	
	
	public static String toString(Puzzle newPuzzle)
	{
		String toString = "";
		try {
			ObjectMapper mapper = new ObjectMapper();
			toString = mapper.writeValueAsString(newPuzzle);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return toString;
	}

}
