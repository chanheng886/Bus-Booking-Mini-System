import 'package:flutter/material.dart';
import 'package:frontend/models/auth_request_model.dart';
import 'package:frontend/services/auth_api_service.dart';

class AuthViewmodel extends ChangeNotifier {
  final AuthApiService _apiService = AuthApiService();

  bool _isLogin = true;
  bool _isLoading = false;
  bool _obscurePassword = true;
  String? _errorMessage;
  UserModel? _currentUser;

  bool get isLogin => _isLogin;
  bool get isLoading => _isLoading;
  bool get obscurePassword => _obscurePassword;
  String? get errorMessage => _errorMessage;
  UserModel? get currentUser => _currentUser;

  void toggleAuthMode(bool isLoginMode) {
    _isLogin = isLoginMode;
    _errorMessage = null;
    notifyListeners();
  }

  void togglePasswordVisbility() {
    _obscurePassword = !_obscurePassword;
    notifyListeners();
  }

  Future<bool> executeLogin(String email, String password) async {
    _setLoading(true);
    try {
      final request = LoginRequest(email: email.trim(), password: password);
      _currentUser = await _apiService.login(request);
      _errorMessage = null;
      _setLoading(false);
      return true;
    } catch (e) {
      _errorMessage = e.toString().replaceFirst('Exception', ' ');
      _setLoading(false);
      return false;
    }
  }

  Future<bool> executeRegister({
    required String email,
    required password,
    required confirmPassword,
  }) async {
    _setLoading(true);
    try {
      final request = RegisterRequest(
        email: email.trim(),
        password: password,
        confirmPassword: confirmPassword,
      );
      _currentUser = await _apiService.regisrer(request);
      _setLoading(false);
      return true;
    } catch (e) {
      _errorMessage = e.toString().replaceFirst('Exception ', ' ');
      _setLoading(false);
      return false;
    }
  }

  void _setLoading(bool value) {
    _isLoading = true;
    notifyListeners();
  }
}
