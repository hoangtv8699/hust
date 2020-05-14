ORG 0000h                
    MOV P1, #0ffh            
    MOV P0, #00            
POLL:
    MOV A, P1
	CJNE A, #00h, ZONE1                   
    LJMP POLL
	
ZONE1:
	CJNE A, #01h, ZONE2
	MOV A, #0xF9
	MOV P0, A
   	LJMP ALARM
ZONE2:
	CJNE A, #02h, ZONE3
	MOV A, #0xA4
	MOV P0, A
	LJMP ALARM
ZONE3:
	CJNE A, #04h, ZONE4
	MOV A, #0xB0
	MOV P0, A
	LJMP ALARM
ZONE4:
	MOV A, #0x99
	MOV P0, A
	LJMP ALARM		
	
	                
ALARM:
    SETB P0.7                
END_LOOP:
    LJMP END_LOOP            
END      									      																   									