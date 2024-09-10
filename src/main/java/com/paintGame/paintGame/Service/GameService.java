package com.paintGame.paintGame.Service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameService {

    private List<Integer> occupiedSquares = new ArrayList<>();

    public List<Integer> getOccupiedSquares() {
        return new ArrayList<>(occupiedSquares); 
    }

    public void assignSquare(int squareId) {
        if (!occupiedSquares.contains(squareId)) {
            occupiedSquares.add(squareId);
        }
    }
    public void freeSquare(int squareId) {
        occupiedSquares.remove(Integer.valueOf(squareId));
    }

    public void resetSquares() {
        occupiedSquares.clear();
    }

}
