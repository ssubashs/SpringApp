package com.app.web.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.sudoku.ISudokuPuzzleService;
import com.app.sudoku.Puzzle;
import com.app.sudoku.SudokuInmemoryService;

@Controller
@RequestMapping(value = "/sudoku")
public class SudokuController {
	
	@Autowired
	ISudokuPuzzleService sudokuDbService;
	
	@RequestMapping(headers={"X-Requested-With!=XMLHttpRequest", "Accept=text/html"})
	public String renderSudokuView(Model model)
	{
		
		return "games/Sudoku";
	}
	
	@RequestMapping(value="/{id}",headers={"X-Requested-With!=XMLHttpRequest", "Accept=text/html"})
	public String renderSudokuViewForId(Model model,@PathVariable String id)
	{
		Puzzle temp;
		try
		{
		  temp = sudokuDbService.getPuzzle(id);
		  if(temp == null)
				temp = sudokuDbService.getRandomPuzzle();
		  
		  ObjectMapper mapper = new ObjectMapper();
		  model.addAttribute("puzzle", mapper.writeValueAsString(temp.getGrid()));
		  model.addAttribute("id", id);
		  
		}
		catch(Exception excep) // uid not correct
		{
			temp = sudokuDbService.getRandomPuzzle();
		}
		
		return "games/Sudoku";
	}
	
	@RequestMapping(headers={"X-Requested-With=XMLHttpRequest"},produces="text/html")
	public String renderSudokuGrid(Model model)
	{
		
		
		return "games/partial/Sudoku";
	}
	
	// to be added after testing //,headers={"X-Requested-With=XMLHttpRequest"} 
	@RequestMapping(value="/puzzle/{id}",headers={"X-Requested-With=XMLHttpRequest"})
	@ResponseBody
	public ResponseEntity<Puzzle> getPuzzle(@PathVariable String id,HttpServletRequest request, HttpServletResponse response)
	{
		Puzzle temp;	
		
		try
		{
		  temp = sudokuDbService.getPuzzle(id);
		  if(temp == null)
				temp = sudokuDbService.getRandomPuzzle();
		}
		catch(Exception excep) // uid not correct
		{
			temp = sudokuDbService.getRandomPuzzle();
		}
		 
		 HttpHeaders responseHeaders = new HttpHeaders();
		   
		  Enumeration headerNames = request.getHeaderNames();
		  while(headerNames.hasMoreElements()){
		   String nextElement = (String)headerNames.nextElement();
		   System.out.println(nextElement + "=" + request.getHeaders(nextElement));
		   responseHeaders.set(nextElement, request.getHeader(nextElement));
		  }
		   
		   
		  //populating the header required for CORS
		  responseHeaders.set("Access-Control-Allow-Origin", "*"); 
		return new ResponseEntity<Puzzle>(temp,responseHeaders,HttpStatus.OK);
	}
	
	@RequestMapping(value="/puzzle/create",method=RequestMethod.POST)
	@ResponseBody
	public Puzzle savePuzzle(Model model,@RequestBody Puzzle puzzle)
	{
		System.out.println("saving the puzzle" + SudokuInmemoryService.toString(puzzle));
		Puzzle temp = sudokuDbService.savePuzzle(puzzle);
		return temp;
	}
	


	 



}
