<template>
  <div class="login-view">
    <div class="welcome-side">
      <div class="welcome-content">
        <h1>Estoque Manager</h1>
        <p>Sistema de gerenciamento de estoque simples e eficiente</p>

        <div class="features">
          <div class="feature-item">
            <span class="feature-icon"><svg width="24" height="24" xmlns="http://www.w3.org/2000/svg" fill-rule="evenodd" clip-rule="evenodd"><path d="M7 24h-5v-9h5v1.735c.638-.198 1.322-.495 1.765-.689.642-.28 1.259-.417 1.887-.417 1.214 0 2.205.499 4.303 1.205.64.214 1.076.716 1.175 1.306 1.124-.863 2.92-2.257 2.937-2.27.357-.284.773-.434 1.2-.434.952 0 1.751.763 1.751 1.708 0 .49-.219.977-.627 1.356-1.378 1.28-2.445 2.233-3.387 3.074-.56.501-1.066.952-1.548 1.393-.749.687-1.518 1.006-2.421 1.006-.405 0-.832-.065-1.308-.2-2.773-.783-4.484-1.036-5.727-1.105v1.332zm-1-8h-3v7h3v-7zm1 5.664c2.092.118 4.405.696 5.999 1.147.817.231 1.761.354 2.782-.581 1.279-1.172 2.722-2.413 4.929-4.463.824-.765-.178-1.783-1.022-1.113 0 0-2.961 2.299-3.689 2.843-.379.285-.695.519-1.148.519-.107 0-.223-.013-.349-.042-.655-.151-1.883-.425-2.755-.701-.575-.183-.371-.993.268-.858.447.093 1.594.35 2.201.52 1.017.281 1.276-.867.422-1.152-.562-.19-.537-.198-1.889-.665-1.301-.451-2.214-.753-3.585-.156-.639.278-1.432.616-2.164.814v3.888zm3.79-19.913l3.21-1.751 7 3.86v7.677l-7 3.735-7-3.735v-7.719l3.784-2.064.002-.005.004.002zm2.71 6.015l-5.5-2.864v6.035l5.5 2.935v-6.106zm1 .001v6.105l5.5-2.935v-6l-5.5 2.83zm1.77-2.035l-5.47-2.848-2.202 1.202 5.404 2.813 2.268-1.167zm-4.412-3.425l5.501 2.864 2.042-1.051-5.404-2.979-2.139 1.166z"/></svg></span>
            <span>Controle de produtos</span>
          </div>
          <div class="feature-item">
            <span class="feature-icon"><svg width="24" height="24" xmlns="http://www.w3.org/2000/svg" fill-rule="evenodd" clip-rule="evenodd"><path d="M12.434 22.586l7.859-7.858.707.707-8.565 8.565-.001-.001v.001l-12.434-12.434.707-.707 11.727 11.727zm-.033-1.7l-12.401-12.405v-8.481h8.441l12.445 12.401-8.485 8.485zm-4.373-19.886h-7.028v7.067l11.401 11.405 7.07-7.07s-7.534-7.506-11.443-11.402zm-1.598 2.594c.78.78.78 2.048 0 2.828-.781.781-2.048.781-2.829 0-.78-.78-.78-2.048 0-2.828.781-.781 2.048-.781 2.829 0zm-.707.707c.39.39.39 1.024 0 1.414-.391.39-1.024.39-1.414 0-.391-.39-.391-1.024 0-1.414.39-.39 1.023-.39 1.414 0z"/></svg></span>
            <span>Gestão de categorias</span>
          </div>
          <div class="feature-item">
            <span class="feature-icon"><svg width="24" height="24" xmlns="http://www.w3.org/2000/svg" fill-rule="evenodd" clip-rule="evenodd"><path d="M12.01 20c-5.065 0-9.586-4.211-12.01-8.424 2.418-4.103 6.943-7.576 12.01-7.576 5.135 0 9.635 3.453 11.999 7.564-2.241 4.43-6.726 8.436-11.999 8.436zm-10.842-8.416c.843 1.331 5.018 7.416 10.842 7.416 6.305 0 10.112-6.103 10.851-7.405-.772-1.198-4.606-6.595-10.851-6.595-6.116 0-10.025 5.355-10.842 6.584zm10.832-4.584c2.76 0 5 2.24 5 5s-2.24 5-5 5-5-2.24-5-5 2.24-5 5-5zm0 1c2.208 0 4 1.792 4 4s-1.792 4-4 4-4-1.792-4-4 1.792-4 4-4z"/></svg></span>
            <span>Visualização do Estoque</span>
          </div>
        </div>
      </div>
    </div>

    <div class="auth-side">
      <div class="auth-container">
        <Transition name="fade-slide" mode="out-in">
          <div v-if="!isRegister" key="login" class="auth-form-container">
            <h2>Bem-vindo de volta!</h2>
            <p class="auth-subtitle">Faça login para acessar o sistema</p>

            <form @submit.prevent="handleLogin" class="auth-form">
              <div class="form-group">
                <label for="usuario">Usuário</label>
                <input
                  type="text"
                  id="usuario"
                  v-model="loginForm.usuario"
                  placeholder="Digite seu usuário"
                  required
                />
              </div>

              <div class="form-group">
                <label for="senha">Senha</label>
                <input
                  type="password"
                  id="senha"
                  v-model="loginForm.senha"
                  placeholder="Digite sua senha"
                  required
                />
              </div>

              <button type="submit" class="btn-login">Entrar</button>
            </form>

            <p class="switch-text">
              Não tem uma conta?
              <a href="#" @click.prevent="showRegister">Criar conta</a>
            </p>
          </div>

          <div v-else key="register" class="auth-form-container">
            <h2>Criar nova conta</h2>
            <p class="auth-subtitle">Preencha seus dados para se registrar</p>

            <form @submit.prevent="handleRegister" class="auth-form" autocomplete="off">
              <div class="form-group">
                <label for="nome">Nome completo</label>
                <input
                  type="text"
                  id="nome"
                  v-model="registerForm.nome"
                  placeholder="Digite seu nome completo"
                  required
                />
              </div>

              <div class="form-group">
                <label for="usuario-reg">Usuário</label>
                <input
                  type="text"
                  id="usuario-reg"
                  v-model="registerForm.usuario"
                  placeholder="Escolha um usuário"
                  required
                />
              </div>

              <div class="form-group">
                <label for="email">E-mail</label>
                <input
                  type="email"
                  id="email"
                  v-model="registerForm.email"
                  placeholder="Digite seu e-mail"
                  required
                />
              </div>

              <div class="form-group">
                <label for="cargo">Cargo</label>
                <select id="cargo" v-model="registerForm.cargo" required>
                  <option value="">Selecione um cargo</option>
                  <option value="Operador de Estoque">Operador de Estoque</option>
                  <option value="Gerente de Estoque">Gerente de Estoque</option>
                  <option value="Comprador">Comprador</option>
                  <option value="Administrador">Administrador</option>
                </select>
              </div>

              <div class="form-row">
                <div class="form-group half">
                  <label for="senha-reg">Senha</label>
                  <input
                    :type="showPassword ? 'text' : 'password'"
                    id="senha-reg"
                    v-model="registerForm.senha"
                    placeholder="Mínimo 6 caracteres"
                    autocomplete="new-password"
                    required
                  />
                </div>

                <div class="form-group half">
                  <label for="confirmar-senha">Confirmar Senha</label>
                  <input
                    :type="showPassword ? 'text' : 'password'"
                    id="confirmar-senha"
                    v-model="registerForm.confirmarSenha"
                    placeholder="Confirme"
                    autocomplete="new-password"
                    required
                  />
                </div>
              </div>

              <button type="submit" class="btn-login">Registrar</button>
            </form>

            <p class="switch-text">
              Já tem uma conta?
              <a href="#" @click.prevent="showLogin">Fazer login</a>
            </p>
          </div>
        </Transition>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'LoginView',
  data() {
  return {
    isRegister: false,
    loginForm: {
      usuario: '',
      senha: '',
    },
    registerForm: {
      nome: '',
      usuario: '',
      email: '',
      cargo: '',
      senha: '',
      confirmarSenha: '',
    },
  }
},
  methods: {
    showRegister() {
      this.isRegister = true
    },
    showLogin() {
      this.isRegister = false
      this.registerForm = {
        nome: '',
        usuario: '',
        email: '',
        cargo: '',
        senha: '',
        confirmarSenha: '',
      }
    },
    handleLogin() {
      const usuario = "admin"
      const senha = "123456"

      if (
        this.loginForm.usuario === usuario &&
        this.loginForm.senha === senha
      ) {

        localStorage.setItem("auth", "true")

        this.$router.push('/categorias')

      } else {
        alert("Usuário ou senha inválidos")
      }
    },
    handleRegister() {
      if (this.registerForm.senha !== this.registerForm.confirmarSenha) {
        alert('As senhas não coincidem!')
        return
      }

      if (this.registerForm.senha.length < 6) {
        alert('A senha deve ter no mínimo 6 caracteres!')
        return
      }

      if (!this.registerForm.email.includes('@')) {
        alert('Digite um e-mail válido!')
        return
      }

      console.log('Registro:', this.registerForm)
      alert('Cadastro realizado com sucesso! Faça o login.')
      this.showLogin()
    },
  },
}
</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

.login-view {
  display: flex;
  width: 100vw;
  height: 100vh;
  position: fixed;
  background-color: var(--branco);
  top: 0;
  left: 0;
  margin: 0;
  padding: 0;
  overflow: hidden;
}

.welcome-side {
  flex: 1;
  background: linear-gradient(135deg, var(--azul-escuro) 0%, var(--azul-padrao) 100%);
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 40px;
  border-radius: 0 40px 40px 0;
  background-size: 300% 300%;
  animation: gradientMove 10s ease infinite;
}

.welcome-content {
  max-width: 500px;
  color: var(--branco);
}

.welcome-content h1 {
  font-size: 3rem;
  font-weight: 700;
  margin-bottom: 20px;
  line-height: 1.2;
}

.welcome-content p {
  font-size: 1.2rem;
  margin-bottom: 40px;
  color: var(--branco-contraste);
  opacity: 0.9;
}

.features {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.feature-item {
  display: flex;
  align-items: center;
  gap: 15px;
  font-size: 1.1rem;
  background: rgba(255, 255, 255, 0.1);
  padding: 15px 20px;
  border-radius: 10px;
  backdrop-filter: blur(10px);
  border: 1px solid rgba(255, 255, 255, 0.1);
  animation: floatItem 4s ease-in-out infinite;
}


.feature-icon {
  font-size: 1.8rem;
}

.auth-side {
  flex: 1;
  background: var(--branco);
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 40px;
}

.auth-container {
  max-width: 400px;
  width: 100%;
}

.auth-form-container {
  width: 100%;
}

.auth-form-container h2 {
  font-size: 2rem;
  color: var(--azul-escuro);
  margin-bottom: 10px;
  font-weight: 600;
}

.auth-subtitle {
  color: var(--cinza-claro);
  font-size: 1rem;
  margin-bottom: 30px;
}

.auth-form {
  width: 100%;
}

.form-group {
  margin-bottom: 20px;
}

.form-group select {
  background-image: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='20' height='20' fill='gray' viewBox='0 0 24 24'%3E%3Cpath d='M7 10l5 5 5-5z'/%3E%3C/svg%3E");
  background-repeat: no-repeat;
  background-position: right 12px center;
  background-size: 18px;
}

.form-row {
  display: flex;
  gap: 15px;
}

.form-group.half {
  flex: 1;
}

label {
  display: block;
  margin-bottom: 8px;
  color: var(--cinza-escuro);
  font-weight: 500;
  font-size: 0.95rem;
}

input,
select {
  appearance: none;
  -webkit-appearance: none;
  -moz-appearance: none;
  width: 100%;
  padding: 14px 16px;
  border: 2px solid var(--branco-contraste);
  border-radius: 12px;
  font-size: 1rem;
  transition: all 0.3s ease;
  background: var(--branco);
  font-family: 'Rubik', sans-serif;
  padding-right: 40px;
  cursor: pointer;
}

input:focus,
select:focus {
  border-color: var(--azul-claro);
  outline: none;
  box-shadow: none;
  transform: scale(1.02);
  transition: transform 0.2s ease;
}

input::placeholder {
  color: var(--cinza-claro);
  opacity: 0.7;
}

.btn-login {
  width: 100%;
  padding: 16px;
  background: linear-gradient(135deg, var(--azul-padrao) 0%, var(--azul-claro) 100%);
  color: var(--branco);
  border: none;
  border-radius: 12px;
  font-size: 1.1rem;
  font-weight: 600;
  cursor: pointer;
  transition: all 0.3s ease;
  margin-top: 10px;
  font-family: 'Rubik', sans-serif;
}

.btn-login:hover {
  transform: translateY(-2px);
  box-shadow: 0 10px 20px rgba(33, 141, 225, 0.3);
}

.switch-text {
  text-align: center;
  margin-top: 25px;
  color: var(--cinza-escuro);
  font-size: 0.95rem;
}

.switch-text a {
  color: var(--azul-claro);
  text-decoration: none;
  font-weight: 600;
  margin-left: 5px;
  cursor: pointer;
}

.switch-text a:hover {
  text-decoration: none;
}

html,
body {
  overflow: hidden;
}

.fade-slide-enter-active,
.fade-slide-leave-active {
  transition: all 0.3s ease;
}

.fade-slide-enter-from {
  opacity: 0;
  transform: translateX(30px);
}

.fade-slide-leave-to {
  opacity: 0;
  transform: translateX(-30px);
}

.auth-form-container {
  animation: fadeIn 0.5s ease;
}

@keyframes fadeIn {
  from {
    opacity: 0;
    transform: translateY(10px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

.switch-text a {
  transition: all 0.2s ease;
  position: relative;
}

.switch-text a::after {
  content: '';
  position: absolute;
  bottom: -2px;
  left: 0;
  width: 0;
  height: 2px;
  background: var(--azul-claro);
  transition: width 0.3s ease;
}

.switch-text a:hover::after {
  width: 100%;
}

@keyframes gradientMove {
  0% {
    background-position: 0% 50%;
  }
  50% {
    background-position: 100% 50%;
  }
  100% {
    background-position: 0% 50%;
  }
}

@keyframes floatItem {
  0% {
    transform: translateY(0px);
  }

  50% {
    transform: translateY(-8px);
  }

  100% {
    transform: translateY(0px);
  }
}
</style>