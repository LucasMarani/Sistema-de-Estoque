import { createRouter, createWebHistory } from 'vue-router'
import Login from '../views/Login.vue'
import Categorias from '../views/Categorias.vue'

const routes = [
  {
    path: '/',
    redirect: '/login'
  },
  {
    path: '/login',
    name: 'login',
    component: Login
  },
  {
    path: '/categorias',
    name: 'categorias',
    component: Categorias,
    meta: { requiresAuth: true }
  },
  {
    path: '/produtos',
    name: 'produtos',
    component: () => import('../views/Produtos.vue'),
    meta: { requiresAuth: true }
  },
  {
    path: '/produto-form',
    name: 'produto-form',
    component: () => import('../views/ProdutoForm.vue'),
    meta: { requiresAuth: true }
  },
  {
    path: '/produto-form/:id',
    name: 'produto-form-editar',
    component: () => import('../views/ProdutoForm.vue'),
    meta: { requiresAuth: true }
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

router.beforeEach((to, from, next) => {
  const isAuthenticated = localStorage.getItem("auth") === "true"

  if (to.meta.requiresAuth && !isAuthenticated) {
    return next('/login')
  }

  if (to.path === '/login' && isAuthenticated) {
    return next('/categorias')
  }

  next()
})

export default router
