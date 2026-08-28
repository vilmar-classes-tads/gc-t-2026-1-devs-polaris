# Evidências de Correção – Avaliação 1 (Gerência de Configuração e Testes)

## Objetivo
Cada apontamento da Avaliação 1 foi tratado por meio de uma **Issue**, conforme solicitado pelo professor. A imagem anexada demonstra as Issues criadas e encerradas para cada correção.

## Evidências

### Issue 1 – Pastas `target` e `.idea` no `.gitignore`

**Problema:** As pastas `target` e `.idea` precisavam ser ignoradas pelo Git.

**Correção realizada:**
- Correções feitas na branch **`fix/gitignore`**.
- Foram adicionadas as pastas `target` e `.idea` ao arquivo `.gitignore`.
- Commit: **a8c8e4d**

---

### Issue 2 – Protection Rules da branch `main`

**Problema:** A branch `main` não possuía regras de proteção.

**Correção realizada:**
- Configuradas as *Protection Rules* para a branch `main`.
- A alteração foi registrada e concluída por meio da Issue correspondente.

---

### Issue 3 – Arquivo `changelog.md`

**Problema:** O arquivo `changelog.md` não foi encontrado.

**Correção realizada:**
- Arquivo criado/adicionado ao repositório.
- Commit: **968f9f3**

---

### Issue 4 – Branch `main` vazia e ausência de entrega

**Problema:** Não havia tag de entrega e a branch `main` encontrava-se vazia.

**Correção realizada:**
- Criada a branch **`release-v0.2.0`**, destinada à consolidação da entrega.
- A release reúne a versão final do projeto para preenchimento da `main`.

---

### Issue 5 – Integração com Docker

**Problema:** Não foi identificada integração com Docker.

**Correção realizada:**
- Implementada a integração utilizando as branches:
  - `docker-github-actions`
  - `fix/docker-guthub-actions`

Além disso, a branch **`feature/docker-setup`** foi utilizada para remover as pastas anteriormente versionadas, preparando a estrutura para a configuração correta do Docker.

---

# Evidências Visuais

As capturas de tela anexadas demonstram:

1. Todas as Issues criadas para correção dos cinco apontamentos da Avaliação 1 e seu encerramento.
2. As branches utilizadas durante o processo de correção, incluindo:
   - `release-v0.2.0`
   - `develop`
   - `feature/docker-setup`
   - `docker-github-actions`
   - `fix/docker-guthub-actions`

## Resumo

| Item | Situação | Evidência |
|------|----------|-----------|
| 1. `.gitignore` (`target` e `.idea`) | Corrigido | Branch `fix/gitignore` – Commit `a8c8e4d` |
| 2. Protection Rules | Corrigido | Issue correspondente |
| 3. `changelog.md` | Corrigido | Commit `968f9f3` |
| 4. Release / Main | Corrigido | Branch `release-v0.2.0` |
| 5. Docker | Corrigido | Branches `docker-github-actions` e `fix/docker-guthub-actions` |

