<%-- 
    Document   : footer
    Created on : Jul 26, 2016, 5:34:06 PM
    Author     : joshuaduncan
--%>

<jsp:include page="/login.jsp"></jsp:include>

<div class="panel panel-default">
    <div class="panel-body text-center">
        <a href="#myLocation" data-toggle="modal"><span class="glyphicon glyphicon-globe"></span> Locate us!</a>
        
            <a href="#myModal" data-toggle="modal"><span class="glyphicon glyphicon-envelope"></span> Contact us!</a>
                <div class="modal fade" id="myLocation" role="dialog">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <h4 class="modal-title">Locations</h4>
                            </div>
                            <div class="modal-body">
                                <div class="panel panel-default">
                                    <div class="panel-body">
                                        <div class="well text-justify">
                                            <a href="https://www.google.com/maps/place/584+Haight+St,+San+Francisco,+CA+94117/
                                               @37.7698259,-122.4522997,17z/data=!3m1!4b1!4m5!3m4!1s0x80858752532a6feb:0x378fa
                                               573b65f1729!8m2!3d37.7698259!4d-122.450111"
                                               target="_blank">
                                                <b>  <p>Come visit our Store!</p></b></a>
                                            <p>584 Haight St.</p>
                                            <p>San Francisco, CA 94117</p>
                                            <p>phone number</p>
                                        </div>
                                        <div class="well">
                                            <p>We're also at these shows! Come say hello!</p>
                                            <p>Date: </p>
                                            <p>Show address</p>
                                            <p>Booth number:</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-envelope"></span> Contact Us</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    <div class="col-sm-1"></div>
</div>
</body>
</html>
