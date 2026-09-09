class LoginRequest {
  final String email;
  final String password;

  LoginRequest({required this.email, required this.password});
  Map<String, dynamic> toJson() => {'email': email, 'password': password};
  factory LoginRequest.fromJson(Map<String, dynamic> json) {
    return LoginRequest(email: json['email'], password: json['password']);
  }
}

class RegisterRequest {
  final String? userName;
  final String? phoneNumber;
  final String email;
  final String password;
  final String confirmPassword;

  RegisterRequest({
    this.userName,
    this.phoneNumber,
    required this.email,
    required this.password,
    required this.confirmPassword,
  });

  factory RegisterRequest.fromJson(Map<String, dynamic> json) {
    return RegisterRequest(
      phoneNumber: json['phoneNumber'],
      email: json['email'],
      password: json['password'],
      confirmPassword: json['confirmPassword'],
    );
  }
}

class UserModel {
  final int id;
  final String fullName;
  final String email;
  final String token;

  UserModel({
    required this.id,
    required this.fullName,
    required this.email,
    required this.token,
  });

  Map<String, dynamic> tojson() => {
    'id': id,
    'fullName': fullName,
    'email': email,
    'token': token,
  };
  factory UserModel.fromJson(Map<String, dynamic> json) {
    return UserModel(
      id: json['id'],
      fullName: json['fullName'],
      email: json['email'],
      token: json['token'],
    );
  }
}
