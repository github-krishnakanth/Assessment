package info.influx.Nearpod.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import info.influx.Nearpod.model.user.TokenPayLoadInfo;
import info.influx.Nearpod.service.impl.user.UserTokenSessionServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/auth")
@CrossOrigin(value = "*")
@Api(value = "Authentication API", description = "Authenticate user using autherization token.")
public class AuthenticationController {
	
	private static final Logger logger = Logger.getLogger(AuthenticationController.class);
	
	@Autowired
	private UserTokenSessionServiceImpl userTokenSessionService;
	
	@ApiOperation(value = "Authenticated User Login", response = TokenPayLoadInfo.class)
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message="Success", response = TokenPayLoadInfo.class),
			@ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 403, message = "Forbidded"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Failure")})
	public ResponseEntity<TokenPayLoadInfo> login(@RequestBody TokenPayLoadInfo tokenPayLoad){
		return new ResponseEntity(userTokenSessionService.saveUserTokenSessionMapping(tokenPayLoad), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Validate the given token", response = TokenPayLoadInfo.class)
	@RequestMapping(value = "/validateToken", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({
		@ApiResponse(code = 200, message = "Success", response = TokenPayLoadInfo.class),
		@ApiResponse(code = 401, message = "Unauthorized"),
		@ApiResponse(code = 403, message = "Forbidded"),
		@ApiResponse(code = 404, message = "Not Found"),
		@ApiResponse(code = 500, message = "Failure")})
	public ResponseEntity<TokenPayLoadInfo> validateToken(@RequestBody TokenPayLoadInfo tokenPayLoad){
		TokenPayLoadInfo tokenPayLoadInfo = userTokenSessionService.validateTokenandDeleteIfnotValid(tokenPayLoad);
		if(tokenPayLoadInfo.getValid()) {
			return new ResponseEntity<>(tokenPayLoadInfo, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(tokenPayLoadInfo, HttpStatus.UNAUTHORIZED);
		}
	}
}
