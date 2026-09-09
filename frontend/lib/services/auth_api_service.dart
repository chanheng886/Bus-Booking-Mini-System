import 'dart:convert';

import 'package:frontend/models/auth_request_model.dart';
import 'package:http/http.dart' as http;

class AuthApiService {
  static const _baseUrl = 'http://172.16.104.49/api/v1/auth';

  // Fetch data from backend ✅Login
  Future<UserModel> login(LoginRequest request) async {
    final response = await http.post(
      Uri.parse('$_baseUrl/login'),
      headers: {'Content-Type': 'application/json'},
      body: jsonEncode(request.toJson()),
    );
    final Map<String, dynamic> data = jsonDecode(response.body);
    if (response.statusCode == 200) {
      return UserModel.fromJson(data);
    } else {
      throw Exception(
        data['message'] ??
            'Authentication falied with status: ${response.statusCode}',
      );
    }
  }

  // Fetch data from backend ✅ Register
  Future<UserModel> regisrer(RegisterRequest request) async {
    final response = await http.post(
      Uri.parse('$_baseUrl/register'),
      headers: {'Content-Type': 'application/json'},
    );
    final Map<String, dynamic> data = jsonDecode(response.body);

    if (response.statusCode == 200) {
      return UserModel.fromJson(data);
    } else {
      throw Exception(
        data['message'] ??
            'Authentication failed to load with status: ${response.statusCode}',
      );
    }
  }
}
