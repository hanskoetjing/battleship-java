package org.scrum.psd.battleship.controller.dto;

import java.util.ArrayList;
import java.util.List;

public class Ship {
    private boolean isPlaced;
    private String name;
    private int size;
    private List<Position> positions;
    private Color color;
    private boolean isBlown;

    public Ship() {
        this.positions = new ArrayList<>();
    }

    public Ship(String name, int size) {
        this();

        this.name = name;
        this.size = size;
        this.isBlown = false;
    }

    public Ship(String name, int size, List<Position> positions) {
        this(name, size);

        this.positions = positions;
    }

    public Ship(String name, int size, Color color) {
        this(name, size);

        this.color = color;
    }

    public void addPosition(String input) {
        if (positions == null) {
            positions = new ArrayList<>();
        }

        Letter letter = Letter.valueOf(input.toUpperCase().substring(0, 1));
        int number = Integer.parseInt(input.substring(1));

        positions.add(new Position(letter, number));
    }
    
    public List<Position> getCurrentPosition()
    {
    	return positions;
    }
    
    public boolean isValidPosition(Position pos)
    {
    	boolean isTrue = true;
    	if(positions == null)
    	{
    		return true;
    	}
    	else
    	{
    		if(positions.size() == 1)
    		{
    			Position firstPos = positions.get(0);
    			Letter col = firstPos.getColumn();
    	    	int row = firstPos.getRow();
    			if((col.equals(pos.getColumn()) || row == pos.getRow()) && (pos.getRow() - row == 1  || Letter.getNum(pos.getColumn().toString()) - Letter.getNum(col.toString()) == 1))
    	    	{
    	    		isTrue = true;
    	    	}
    	    	else
    	    	{
    	    		isTrue = false;
    	    	}
    		}
    		else if(positions.size() >= 2)
    		{
    			char isColOrRow  = 'u';
    			int lastPosIndex = positions.size() - 1;
    			int twoLastPosIndex = positions.size() - 2;
    			Position lastPos = positions.get(lastPosIndex);
    			Position twoLastPos = positions.get(twoLastPosIndex);

    			if(lastPos.getColumn() == twoLastPos.getColumn())
    				isColOrRow = 'c';
    			else
    				isColOrRow = 'r';
    			
    			if(isColOrRow == 'c' && lastPos.getColumn() == pos.getColumn() && pos.getRow() - lastPos.getRow() == 1) 
    			{
    				isTrue = true;
    			}
    			else if(isColOrRow == 'r' && lastPos.getRow() == pos.getRow() && Letter.getNum(pos.getColumn().toString()) - Letter.getNum(lastPos.getColumn().toString()) == 1)
    			{
    				isTrue = true;
    			}
    			else
    			{
    				isTrue = false;
    			}
    		}
    		
    	}
	    
    	
    	return isTrue;
    }

    // TODO: property change listener implementieren

    public boolean isPlaced() {
        return isPlaced;
    }

    public void setPlaced(boolean placed) {
        isPlaced = placed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    
    public void setBlown(boolean isBlown)
    {
    	this.isBlown = isBlown;
    }
    
    public boolean getBlown()
    {
    	return this.isBlown;
    }
}
