<%-- 
    Document   : index
    Created on : Aug 22, 2016, 12:04:03 PM
    Author     : joshuaduncan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
        <script type="text/javascript">
   
    
    var frame = {
        sqcNum:1, 
        ack:0,
        pBit:0
    }; 
   
    var trans;
    var timeout;
    var rowAtFrames = [];  // array to keep track of frames already transmitted.
    var rowArFrames = [];  // array to keep track of frams received sucessfully.
    var rowBtFrames = [];  // same as above but for Row B;
    var rowBrFrames = [];  // ""
    
    function dFCA(){
        frame.sqcNum = getFrame().sqcNum +1;
        rowB(frame);
    }
    
    function dFCB(){
        var frame = getFrame();
        frame.pBit = "interrupt";
        rowB(frame);
    }
    
    function simStart(){
        rowA(frame);
        
    }
    
    function transmit(frame){
        
        trans = setInterval( function(){rowA(frame);},10000 );
        counter(frame);
    }
    
    function counter(frame){
        timeout = setTimeout(function(){ time(frame);},11000);
    }
    
    function time(frame){
        clearInterval(timeout);
        clearInterval(trans);
        document.getElementById("error").innerHTML="Did not receive confirmation for "+frame.sqcNum+
                " by end of timer. Sending P-BIT.";
        var frame = getFrame();
        frame.pBit = 1;
        rowB(frame);
    }
    
    function setFrame(frame){
        this.frame = frame;
    }
    function getFrame(){
        return this.frame;
    }
    
    
    function rowA(frame){
        
        if( frame.pBit != 0 ){
            document.getElementById("error").innerHTML="Received P-Bit = "+frame.pBit+", resending frames starting"+
                    " from frame "+getFrame().sqcNum;
            transmit();
        }else if(frame.ack === "REJ"){
            document.getElementById("error").innerHTML="<b>REJ</b> received for frame " +(frame.sqcNum)+
                    ", retransmitting frame " + (getFrame().sqcNum -1);
            transmit();
        }else{
            //  check the acknowledgment of previously sent frame, if frame had been sent.
            if( frame.ack >0 && frame.ack === rowAGetTransFramesAck()){
                rowAreceivedFrames(frame);
                document.getElementById("RR").innerHTML = "Received acknowledgment for frame <b>" + frame.ack + "</b>";  
                document.getElementById("error").innerHTML="";
            }

            //  deactivate last active frame if a frame had been previously activated.
            if( frame.sqcNum >1 ){
                var int = thisFrame.sqcNum - 1;  
                removeRow("A",int);
            }



            if( frame.sqcNum <= 7 && frame.ack > rowAGetTransFramesAck()){
                var row = "rowA" + frame.sqcNum;  // designate which row is being activated in UI;
                document.getElementById(row).className="active";

                rowAtransmitFrame(frame);

                rowAtransmittedFrames(frame);
            }
        }
    }
    function rowAtransmitFrame(frame){
        document.getElementById("success").innerHTML = "Transmitting frame <b>" + frame.sqcNum + "</b>";
        rowB(frame);
    }
    function rowAtransmittedFrames(frame){
        rowAtFrames.push(frame);
    }
    function rowAreceivedFrames(frame){
        rowArFrames.push(frame);
    }
    
    function rowAGetTransFrames(i){
        if( rowAtFrames === null || rowAtFrames.length <= 0 ){
            return 0;
        }else 
        return rowAtFrames[i];
    }
    function rowAGetTransFramesAck(){
        if( rowAtFrames === null || rowAtFrames.length <= 0 ){
            return -1;
        }else 
            return rowAtFrames[rowAtFrames.length -1].ack;
    }
    
    function rowB(frame){
        
        if(frame.pBit === "interrupt"){
            frame.pBit = 0;
        }else { clearInterval(timeout); }
        
        if( frame.pBit === 1 ){
            frame.pBit = rowBGetRcvdFramesSqcNum();
            rowA(frame);
            document.getElementById("error2").innerHTML="Received <b>P-Bit 1</b>, last frame received was frame "+
                    rowBGetRcvdFramesSqcNum();
        }else if( frame.sqcNum > 1+ rowBGetRcvdFramesSqcNum()){  // check for DFCA
            clearInterval(transmit);
            document.getElementById("error2").innerHTML="<b>ERROR</b>: receieved frame " + frame.sqcNum + 
                    " before receiving frame " + (frame.sqcNum-1) + 
                    ", sending REJ.";
            frame.ack = "REJ";
            rowA(frame);
        }else {
            if(  frame.sqcNum >1 ){
                var int = frame.sqcNum - 1;
                removeRow("B",int);
                document.getElementById("error2").innerHTML="";
            }
            var row = "rowB" + frame.sqcNum;

            if( frame.sqcNum <= 7 ) {
                document.getElementById(row).className="active";
                document.getElementById("RR2").innerHTML = "Receiving frame <b>" + frame.sqcNum + "</b>";
                rowBreceivedFrames(frame);
                frame.ack += 1;
                rowA(frame);
                frame.sqcNum += 1;
                setFrame(frame);
                document.getElementById("success2").innerHTML = "Acknowledging frame <b>" + frame.ack + "</b>";
                transmit(frame);
            }
        }
    }
    
    function rowBtransmittedFrames(frame){
        rowBtFrames.push(frame);
    }
    function rowBreceivedFrames(frame){
        rowBrFrames.push(frame);
       
    }
    
    function rowBGetTransFrames(sqcNum){
        return rowBtFrames[sqcNum];
    }
    function rowBGetRcvdFrames(i){
        if( rowBrFrames.length <= 0 ){
            return 0;
        }else
            return rowBrFrames[i];
    }
    function rowBGetRcvdFramesSqcNum(){
        if( rowBrFrames.length <= 0 ){
            return 0;
        }else
            return rowBrFrames[rowBrFrames.length -1].sqcNum;
    }
    
    function removeRow(alpha,integer){
        var row = "row"+alpha+integer;
        document.getElementById(row).className="";
    }
</script>
        <title>CS3590 project: GO-BACK-N ARQ simulation</title>
    </head>
    <body>
        <div class="container-fluid">
            <div class="text">
                <h3 class="text-center text-primary">CS3590 project: GO-BACK-N ARQ simulation</h3>
                <h4>By: Joshua Duncan cq4285</h4>
                <p>This is a demonstration of the GO-BACK-N ARQ(automatic repeat request) error control.</p>
                <p>This simulation creates 7 frames, the transmitter (Row A) 
                    sends one frame every 5 seconds to the receiver (Row B). 
                    Once a Frame is sent the transmitter starts a timer set to 10
                seconds.</p>
                <p>Once the frame is received, the receiver sends the acknowledgment back to the transmitter.</p>
                <p>This model simulates the sliding window flow control.</p>
                <p>Use the Buttons to simulate errors in the data transmissions.</p>
                <p>Results will be displayed in the corresponding message alerts.</p>
            </div>
            <div class="col-sm-4"></div>
            <div class="col-sm-4">
                <button class="btn btn-primary btn-block" id="start" onClick="simStart();">Start</button>
            </div>
        </div>
        <div class="container-fluid">
            <div class="col-sm-4">
                <div class="form-group">
                    <h4 class="text-danger">Damaged Frame:</h4>
                </div>
                <div class="form-group">
                    <button class="btn btn-default" id="DFCA" onClick="dFCA();">Damaged Frame: Case A</button>
                    <p> <b>Case A:</b> Row A sends a frame ( <i>i</i> + 1 ), but B receives 
                        the frame out of order and sends a REJ <i>i</i>. Row A must 
                        retransmit frame <i>i</i> and all subsequent frames.</p>
                </div>
                <div class="form-group">
                    <button class="btn btn-default" id="DFCB" onClick="dFCB()">Damaged Frame: Case B</button>
                    <p><b>Case B:</b> Transmitter does not receive an acknowledgment 
                        from the receiver by the time it's timer expires. RR is sent 
                        with P-Bit set to 1, B returns RR with P-Bit set to <i>i</i>.
                        <i>i</i> being the last frame it has received. A resends
                        frame <i>i</i>.</p>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="form-group">
                    <h4 class="text-danger">Damaged RR:</h4>
                </div>
                <div class="form-group">
                    <button class="btn btn-default" id="DRRA" onClick="dRRA()">Damaged RR: Case A</button>
                    <p> <b>Case A:</b> Row B receives frame <i>i</i> and sends RR 
                        ( <i>i</i> + 1) which suffers an error in transit. Row A
                        receives RR ( <i>i</i> + 2 ) without receiving RR ( <i>i</i> 
                        + 1) before ( <i>i</i> + 1 )'s timer has expired.
                        
                </div>
                <div class="form-group">
                    <button class="btn btn-default">Damaged RR: Case B</button>
                    <p><b>Case B:</b> If Row B fails to acknowledge a successful 
                    receipt of a P-Bit by the end of the P-Bit timer, Row A will 
                    send a new P-Bit with a new timer. This will occur for a 
                    number of iterations (2-times for this simulation), before 
                    initiating a reset procedure.</p>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="form-group">
                    <h4 class="text-danger">Damaged REJ:</h4>
                </div>
                <div class="form-group">
                    <button class="btn btn-default">Damaged REJ: Lost REJ</button>
                    <p><b>Case A:</b> this is the same result as Damaged Frame: Case A</p>
                </div>
            </div>
        </div>
        <div class="container-fluid">
            <div class="text text-center">
                <h3>ROW A messages</h3>
            </div>
            <div class="col-sm-4">
                <div class="alert alert-success">
                    <p id="success"></p>
                </div>
            </div>
            
            <div class="col-sm-4">
                <div class="alert alert-info">
                    <p id="RR"></p>
                </div>
            </div>
            
            <div class="col-sm-4">
                <div class="alert alert-danger">
                    <p id="error"></p>
                </div>
            </div>
            
        </div>
        <div class="col-sm-1"></div>
        <div class="col-sm-5">
            <div class="form-group">
                <label for="rowA"><div class="text-center text-danger">Row A</div></label>
                <div class="pagination pagination-lg" id="rowA">
                    <li id="rowA1" class=""><a href="#">1</a></li>
                    <li id="rowA2" class=""><a href="#">2</a></li>
                    <li id="rowA3" class=""><a href="#">3</a></li>
                    <li id="rowA4" class=""><a href="#">4</a></li>
                    <li id="rowA5" class=""><a href="#">5</a></li>
                    <li id="rowA6" class=""><a href="#">6</a></li>
                    <li id="rowA7" class=""><a href="#">7</a></li>
                </div>
            </div>
       </div>
        
        <div class="col-sm-5">
            <div class="form-group">
                <label for="rowB"><div class="text-center text-danger">Row B</div></label>
                <div class="pagination pagination-lg" id="rowB">
                    <li id="rowB1" class=""><a href="#">1</a></li>
                    <li id="rowB2" class=""><a href="#">2</a></li>
                    <li id="rowB3" class=""><a href="#">3</a></li>
                    <li id="rowB4" class=""><a href="#">4</a></li>
                    <li id="rowB5" class=""><a href="#">5</a></li>
                    <li id="rowB6" class=""><a href="#">6</a></li>
                    <li id="rowB7" class=""><a href="#">7</a></li>
                </div>
            </div>
        </div>
        <div class="col-sm-1"></div>

        <div class="container-fluid">
            
            <div class="col-sm-4">
                <div class="alert alert-danger">
                    <p id="error2"></p>
                </div>
            </div>
            
            <div class="col-sm-4">
                <div class="alert alert-info">
                    <p id="RR2"></p>
                </div>
            </div>
            
            <div class="col-sm-4">
                <div class="alert alert-success">
                    <p id="success2"></p>
                </div>
            </div>
            
            
        </div>
        <div class="text text-center">
                <h3>ROW B messages</h3>
            </div>


    </body>
</html>