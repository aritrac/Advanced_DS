package ctci.solutions.aritra;

/*
 * Question: A monchrome screen is stored as a single array of bytes, allowing eight consecutive pixels to be stored in one byte. The screen has width w, where w is divisible by 8
 * (that is, no byte will be split across rows). The height of the screen of course, can be derived from the length of the array and the width
 * .Implement a function that draws a horizontal line from (x1,y) to (x2,y)
 * 
 * The method signature should look like
 * drawLine(byte[] screen, int width, int x1, int x2, int y)
 * 
 * Solution: A better solution is to recognize that if x1 and x2 are far away from each other, several full bytes will be contained between them. These full bytes can be set
 * one at a time by doing screen[bytes_pos] = 0xFF. The residual start and end of the line can be set using masks.
 */

public class DrawLine {
	void drawLine(byte[] screen, int width, int x1, int x2, int y){
		int start_offset = x1 % 8;
		int first_full_byte = x1 / 8;
		
		if(start_offset != 0){
			first_full_byte++;
		}
		
		int end_offset = x2 % 8;
		int last_full_byte = x2/8;
		if(end_offset != 7){
			last_full_byte--;
		}
		
		//Set full bytes
		for(int b = first_full_byte; b <= last_full_byte; b++){
			screen[(width/8) * y + b] = (byte)0xFF;
		}
		
		//Create masks for start and end of line
		byte start_mask = (byte)(0xFF >> start_offset);
		byte end_mask = (byte)~(0xFF >> (end_offset + 1));
		byte byte_mask;
		//Set start and end of line
		if((x1 / 8) == (x2 / 8)){ //x1 and x2 are in the same byte
			byte_mask = (byte)(start_mask & end_mask);
			screen[(width/8) * y + (x1 / 8)] |= byte_mask;
		}else{
			if(start_offset != 0){
				int byte_number = (width/8) * y + first_full_byte - 1;
				screen[byte_number] |= start_mask;
			}
			if(end_offset != 7){
				int byte_number = (width/8) * y + last_full_byte + 1;
				screen[byte_number] |= end_mask;
			}
		}
	}
}
