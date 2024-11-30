document.addEventListener("DOMContentLoaded", () => {
    carregarTarefas("A Fazer", "#aFazer");
    carregarTarefas("Fazendo", "#fazendo");
    carregarTarefas("Pronto", "#pronto");

    // Carregar tarefas por status
    function carregarTarefas(status, container) {
        fetch(`/api/tarefas?status=${status}`)
            .then(response => response.json())
            .then(tarefas => {
                const containerDiv = document.querySelector(container);
                tarefas.forEach(tarefa => {
                    const card = criarCard(tarefa);
                    containerDiv.appendChild(card);
                });
            })
            .catch(error => console.error("Erro ao carregar tarefas:", error));
    }

    // Criar card de tarefa
    function criarCard(tarefa) {
        const card = document.createElement("div");
        card.classList.add("card", "mb-3", "tarefa-card");
        card.dataset.id = tarefa.id;

        card.innerHTML = `
            <div class="card-body">
                <p><strong>Descrição:</strong> ${tarefa.descricao}</p>
                <p><strong>Setor:</strong> ${tarefa.setor}</p>
                <p><strong>Prioridade:</strong> ${tarefa.prioridade.prioridade}</p>
                <p><strong>Vinculado a:</strong> ${tarefa.usuario.nome}</p>
                <button class="btn btn-danger btn-sm excluir-btn">Excluir</button>
                <button class="btn btn-primary btn-sm status-btn" data-novo-status="Fazendo">Fazendo</button>
                <button class="btn btn-success btn-sm status-btn" data-novo-status="Pronto">Pronto</button>
            </div>
        `;

        // Eventos
        card.querySelector(".excluir-btn").addEventListener("click", () => excluirTarefa(tarefa.id, card));
        card.querySelectorAll(".status-btn").forEach(btn => {
            btn.addEventListener("click", () => alterarStatus(tarefa.id, btn.dataset.novoStatus, card));
        });

        return card;
    }

    // Excluir tarefa
    function excluirTarefa(id, card) {
        fetch(`/api/tarefas/${id}`, { method: "DELETE" })
            .then(response => {
                if (response.ok) {
                    card.remove();
                    alert("Tarefa excluída com sucesso!");
                } else {
                    alert("Erro ao excluir tarefa.");
                }
            })
            .catch(error => console.error("Erro ao excluir tarefa:", error));
    }

    // Alterar status
    function alterarStatus(id, novoStatus, card) {
        fetch(`/api/tarefas/${id}/alterarStatus?novoStatus=${novoStatus}`, { method: "PUT" })
            .then(response => {
                if (response.ok) {
                    card.remove();
                    const novoContainer = document.querySelector(`#${novoStatus.toLowerCase()}`);
                    novoContainer.appendChild(card);
                } else {
                    alert("Erro ao alterar status.");
                }
            })
            .catch(error => console.error("Erro ao alterar status:", error));
    }
});
