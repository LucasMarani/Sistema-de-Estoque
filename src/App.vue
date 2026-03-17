<template>
  <div id="app">
    <nav>
      <div class="nav-container">
        <h1 class="logo">Estoque Manager</h1>

        <div class="nav-links">
          <router-link to="/categorias" class="nav-link" :class="{ 'active': $route.path === '/categorias' }">
            Categorias
            <span class="active-indicator" v-if="$route.path === '/categorias'"></span>
          </router-link>
          
          <router-link to="/produtos" class="nav-link" :class="{ 'active': $route.path === '/produtos' }">
            Produtos
            <span class="active-indicator" v-if="$route.path === '/produtos'"></span>
          </router-link>
          
          <router-link to="/produto-form" class="nav-link" :class="{ 'active': $route.path === '/produto-form' }">
            Novo Produto
            <span class="active-indicator" v-if="$route.path === '/produto-form'"></span>
          </router-link>
        </div>

        <div class="logout" @click="logout">
          <span class="icon">
            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="white">
              <path d="M16 10v-5l8 7-8 7v-5h-8v-4h8zm-16-8v20h14v-2h-12v-16h12v-2h-14z"/>
            </svg>
          </span>
          <span>Sair</span>
        </div>
      </div>
    </nav>

    <main class="container">
      <router-view v-slot="{ Component }">
        <transition name="fade" mode="out-in">
          <component :is="Component" />
        </transition>
      </router-view>
    </main>
  </div>
</template>

<script>
export default {
  name: 'App',
  
  methods: {
    logout() {
      localStorage.removeItem('auth')
      this.$router.push('/login')
    }
  }
}
</script>

<style scoped>
#app {
  min-height: 100vh;
  background-color: var(--branco-contraste);
}

nav {
  background-color: var(--azul-escuro);
  padding: 1rem 0;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.2);
}

.nav-container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 20px;
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.logo {
  color: var(--branco);
  font-size: 1.5rem;
  font-weight: 500;
}

.nav-links {
  display: flex;
  gap: 8px;
}

.nav-link {
  color: var(--branco-contraste);
  text-decoration: none;
  padding: 8px 20px;
  border-radius: 8px;
  position: relative;
  transition: all 0.3s ease;
  font-weight: 500;
}

.nav-link:hover {
  background-color: var(--azul-padrao);
  color: var(--branco);
  transform: translateY(-1px);
}

.nav-link.active {
  color: var(--branco);
  background-color: var(--azul-padrao);
}

.active-indicator {
  position: absolute;
  bottom: 0;
  left: 50%;
  transform: translateX(-50%);
  width: 20px;
  height: 3px;
  background-color: var(--branco);
  border-radius: 3px 3px 0 0;
  animation: slideUp 0.3s ease;
}

@keyframes slideUp {
  from {
    bottom: -10px;
    opacity: 0;
  }
  to {
    bottom: 0;
    opacity: 1;
  }
}

.logout {
  display: flex;
  align-items: center;
  gap: 8px;
  cursor: pointer;
  color: var(--branco-contraste);
  padding: 8px 16px;
  border-radius: 8px;
  transition: all 0.3s ease;
}

.logout:hover {
  background-color: var(--azul-padrao);
  color: white;
  transform: translateY(-1px);
}

.icon {
  font-size: 1.1rem;
  display: flex;
  align-items: center;
}

.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.3s ease;
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}
</style>