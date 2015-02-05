package com.app.sudoku;

import java.util.List;

public interface ISudokuPuzzleService 
{
	public Puzzle getPuzzle(String id);
	public List<Puzzle> getAllPuzzles();
	public Puzzle getRandomPuzzle();
	public Puzzle getPuzzleClass(String difficulty);
	public Puzzle savePuzzle(Puzzle newPuzzle);
	public Puzzle getPuzzleRandom(int percent);
}
