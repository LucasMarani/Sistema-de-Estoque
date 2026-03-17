<template>
  <div class="categorias-view">
    <div class="header">
      <h1>Categorias</h1>
      <button class="btn-primary" @click="abrirFormulario">
        {{ showForm ? 'Cancelar' : 'Nova Categoria' }}
      </button>
    </div>

    <transition name="slide">
      <div v-if="showForm" class="form-container">
        <h3>{{ modoEdicao ? 'Editar Categoria' : 'Criar Categoria' }}</h3>
        
        <div class="form-grid">
          <input
            v-model="categoriaForm.nome"
            placeholder="Nome da categoria"
            @keyup.enter="salvarCategoria"
            ref="inputCategoria"
          />
          
          <input
            v-model="categoriaForm.descricao"
            placeholder="Descrição (opcional)"
          />
          
          <select v-model="categoriaForm.status">
            <option value="Ativa">Ativa</option>
            <option value="Inativa">Inativa</option>
          </select>
        </div>

        <div class="form-actions">
          <button class="btn-primary" @click="salvarCategoria">
            {{ modoEdicao ? 'Atualizar' : 'Salvar' }}
          </button>
          <button v-if="modoEdicao" class="btn-secondary" @click="cancelarEdicao">
            Cancelar
          </button>
        </div>
      </div>
    </transition>

    <div class="categorias-list" ref="listaContainer">
      <div
        v-for="categoria in categorias"
        :key="categoria.id"
        class="categoria-card"
      >
        <div class="categoria-info">
          <div class="categoria-header">
            <span class="categoria-nome">{{ categoria.nome }}</span>
            <span class="categoria-status" :class="categoria.status.toLowerCase()">
              {{ categoria.status }}
            </span>
          </div>
          <span class="categoria-descricao">{{ categoria.descricao || 'Sem descrição' }}</span>
        </div>
        
        <div class="card-actions">
          <button class="btn-edit" @click="editarCategoria(categoria)">
            <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
              <path d="M11 4H4a2 2 0 0 0-2 2v14a2 2 0 0 0 2 2h14a2 2 0 0 0 2-2v-7"></path>
              <path d="M18.5 2.5a2.12 2.12 0 0 1 3 3L12 15l-4 1 1-4Z"></path>
            </svg>
          </button>
          <button class="btn-delete" @click="removerCategoria(categoria.id)">
            <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
              <path d="M3 6h18"></path>
              <path d="M19 6v14a2 2 0 0 1-2 2H7a2 2 0 0 1-2-2V6"></path>
              <path d="M8 4V4a2 2 0 0 1 2-2h4a2 2 0 0 1 2 2v2"></path>
            </svg>
          </button>
        </div>
      </div>
      
      <div v-if="categorias.length === 0" class="empty-message">
        Nenhuma categoria cadastrada
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "CategoriasView",

  data() {
    return {
      showForm: false,
      modoEdicao: false,
      categoriaEditandoId: null,
      categoriaForm: {
        nome: "",
        descricao: "",
        status: "Ativa"
      },
      categorias: [
        { 
          id: 1, 
          nome: "Eletrônicos", 
          descricao: "Produtos eletrônicos em geral",
          status: "Ativa"
        },
        { 
          id: 2, 
          nome: "Alimentos", 
          descricao: "Alimentos não perecíveis",
          status: "Ativa"
        },
        { 
          id: 3, 
          nome: "Limpeza", 
          descricao: "Produtos de limpeza",
          status: "Ativa"
        },
        { 
          id: 4, 
          nome: "Roupas", 
          descricao: "Vestuário em geral",
          status: "Inativa"
        }
      ]
    }
  },

  watch: {
    showForm(val) {
      if (val) {
        this.$nextTick(() => {
          this.$refs.inputCategoria?.focus()
        })
      } else if (!val && !this.modoEdicao) {
        this.limparFormulario()
      }
    }
  },

  methods: {
    abrirFormulario() {
      if (this.showForm && this.modoEdicao) {
        this.cancelarEdicao()
      } else {
        this.showForm = !this.showForm
      }
    },

    editarCategoria(categoria) {
      this.categoriaForm.nome = categoria.nome
      this.categoriaForm.descricao = categoria.descricao || ""
      this.categoriaForm.status = categoria.status
      this.categoriaEditandoId = categoria.id
      this.modoEdicao = true
      this.showForm = true
    },

    salvarCategoria() {
      if (!this.categoriaForm.nome.trim()) {
        alert("Digite um nome para a categoria")
        return
      }

      if (this.modoEdicao) {
        const index = this.categorias.findIndex(c => c.id === this.categoriaEditandoId)
        if (index !== -1) {
          this.categorias[index] = {
            ...this.categorias[index],
            nome: this.categoriaForm.nome.trim(),
            descricao: this.categoriaForm.descricao.trim(),
            status: this.categoriaForm.status
          }
        }
      } else {
        this.categorias.push({
          id: Date.now(),
          nome: this.categoriaForm.nome.trim(),
          descricao: this.categoriaForm.descricao.trim(),
          status: this.categoriaForm.status
        })
      }

      this.limparFormulario()
      this.showForm = false
    },

    cancelarEdicao() {
      this.limparFormulario()
      this.showForm = false
    },

    limparFormulario() {
      this.categoriaForm = {
        nome: "",
        descricao: "",
        status: "Ativa"
      }
      this.categoriaEditandoId = null
      this.modoEdicao = false
    },

    removerCategoria(id) {
      if (confirm('Tem certeza que deseja excluir esta categoria?')) {
        this.categorias = this.categorias.filter(c => c.id !== id)
        
        if (this.modoEdicao && this.categoriaEditandoId === id) {
          this.cancelarEdicao()
        }
      }
    }
  }
}
</script>

<style scoped>
.categorias-view {
  height: 100%;
  padding: 40px;
  background: var(--branco);
  display: flex;
  flex-direction: column;
  overflow: hidden;
}

.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 30px;
  flex-shrink: 0;
}

.header h1 {
  font-size: 2.2rem;
  color: var(--azul-escuro);
  font-weight: 600;
}

.btn-primary {
  padding: 12px 24px;
  background: linear-gradient(135deg, var(--azul-padrao), var(--azul-claro));
  color: white;
  border: none;
  border-radius: 10px;
  font-weight: 600;
  cursor: pointer;
  transition: all 0.25s;
  white-space: nowrap;
  font-size: 1rem;
}

.btn-primary:hover {
  transform: translateY(-2px);
  box-shadow: 0 10px 20px rgba(33, 141, 225, 0.25);
}

.btn-secondary {
  padding: 12px 24px;
  background: #f0f0f0;
  color: #666;
  border: none;
  border-radius: 10px;
  font-weight: 600;
  cursor: pointer;
  transition: all 0.25s;
  white-space: nowrap;
  font-size: 1rem;
}

.btn-secondary:hover {
  background: #e0e0e0;
  transform: translateY(-2px);
}

.form-container {
  background: white;
  padding: 25px;
  border-radius: 15px;
  margin-bottom: 30px;
  box-shadow: 0 10px 30px rgba(0, 0, 0, 0.05);
  display: flex;
  flex-direction: column;
  gap: 15px;
  flex-shrink: 0;
  border: 1px solid #f0f0f0;
}

.form-container h3 {
  font-size: 1.1rem;
  color: #333;
  font-weight: 500;
}

.form-grid {
  display: flex;
  flex-direction: column;
  gap: 10px;
}

.form-container input,
.form-container select {
  padding: 12px 16px;
  border: 2px solid #e0e0e0;
  border-radius: 10px;
  font-family: 'Rubik', sans-serif;
  font-size: 1rem;
  transition: all 0.25s;
}

.form-container input:focus,
.form-container select:focus {
  outline: none;
  border-color: var(--azul-padrao);
  box-shadow: 0 0 0 3px rgba(33, 141, 225, 0.1);
}

.form-actions {
  display: flex;
  gap: 10px;
  justify-content: flex-end;
}

.categorias-list {
  flex: 1;
  overflow-y: auto;
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
  gap: 20px;
  align-content: start;
  padding-right: 8px;
}

.categorias-list::-webkit-scrollbar {
  width: 6px;
}

.categorias-list::-webkit-scrollbar-track {
  background: #f1f1f1;
  border-radius: 10px;
}

.categorias-list::-webkit-scrollbar-thumb {
  background: var(--azul-claro);
  border-radius: 10px;
}

.categorias-list::-webkit-scrollbar-thumb:hover {
  background: var(--azul-padrao);
}

.categoria-card {
  background: white;
  padding: 20px;
  border-radius: 15px;
  box-shadow: 0 5px 20px rgba(0, 0, 0, 0.03);
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
  transition: all 0.25s;
  border: 1px solid #f0f0f0;
}

.categoria-card:hover {
  transform: translateY(-4px);
  box-shadow: 0 15px 30px rgba(0, 0, 0, 0.08);
}

.categoria-info {
  flex: 1;
  margin-right: 15px;
}

.categoria-header {
  display: flex;
  align-items: center;
  gap: 10px;
  margin-bottom: 5px;
  flex-wrap: wrap;
}

.categoria-nome {
  font-weight: 600;
  color: var(--azul-escuro);
  font-size: 1.1rem;
}

.categoria-status {
  font-size: 0.8rem;
  padding: 3px 8px;
  border-radius: 12px;
  font-weight: 500;
}

.categoria-status.ativa {
  background: #e8f5e9;
  color: #2e7d32;
}

.categoria-status.inativa {
  background: #ffebee;
  color: #c62828;
}

.categoria-descricao {
  font-size: 0.9rem;
  color: #666;
  display: block;
}

.card-actions {
  display: flex;
  gap: 8px;
}

.btn-edit, .btn-delete {
  border: none;
  padding: 8px;
  border-radius: 8px;
  cursor: pointer;
  transition: all 0.25s;
  display: flex;
  align-items: center;
  justify-content: center;
}

.btn-edit {
  background: #f0f0f0;
  color: #666;
}

.btn-edit:hover {
  background: var(--azul-claro);
  color: white;
  transform: scale(1.1);
}

.btn-delete {
  background: #ff4d4d;
  color: white;
}

.btn-delete:hover {
  background: #ff3333;
  transform: scale(1.1);
}

.empty-message {
  grid-column: 1 / -1;
  text-align: center;
  padding: 60px 20px;
  color: #999;
  font-size: 1.2rem;
  background: #fafafa;
  border-radius: 15px;
  border: 2px dashed #e0e0e0;
}

.slide-enter-active,
.slide-leave-active {
  transition: all 0.3s ease;
}

.slide-enter-from,
.slide-leave-to {
  opacity: 0;
  transform: translateY(-20px);
}
</style>