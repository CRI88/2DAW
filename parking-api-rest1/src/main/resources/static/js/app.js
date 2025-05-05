document.addEventListener("DOMContentLoaded", () => {
  // Referencias a elementos del DOM
  const ticketTableBody = document.getElementById("ticketTableBody")
  const ticketForm = document.getElementById("ticketForm")
  const ticketModal = new bootstrap.Modal(document.getElementById("ticketModal"))
  const viewTicketModal = new bootstrap.Modal(document.getElementById("viewTicketModal"))
  const deleteConfirmModal = new bootstrap.Modal(document.getElementById("deleteConfirmModal"))
  const loadingIndicator = document.getElementById("loadingIndicator")
  const noTicketsMessage = document.getElementById("noTicketsMessage")
  const toastElement = document.getElementById("liveToast")
  const toast = new bootstrap.Toast(toastElement)
  const toastTitle = document.getElementById("toastTitle")
  const toastMessage = document.getElementById("toastMessage")

  // Variable para almacenar el ID del ticket a eliminar
  let ticketToDelete = null

  // Cargar tickets al iniciar
  loadTickets()

  // Configurar fecha actual por defecto
  document.getElementById("enter_date").valueAsDate = new Date()

  // Configurar hora actual por defecto
  const now = new Date()
  document.getElementById("enter_hour").value =
    `${String(now.getHours()).padStart(2, "0")}:${String(now.getMinutes()).padStart(2, "0")}`

  // Event Listeners
  document.getElementById("btnNewTicket").addEventListener("click", resetForm)
  document.getElementById("saveTicket").addEventListener("click", saveTicket)
  document.getElementById("confirmDelete").addEventListener("click", deleteTicket)

  // Función para cargar todos los tickets
  function loadTickets() {
    loadingIndicator.classList.remove("d-none")
    noTicketsMessage.classList.add("d-none")

    fetch("/api/ticket")
      .then((response) => response.json())
      .then((tickets) => {
        loadingIndicator.classList.add("d-none")
        ticketTableBody.innerHTML = ""

        if (tickets.length === 0) {
          noTicketsMessage.classList.remove("d-none")
          return
        }

        tickets.forEach((ticket) => {
          addTicketToTable(ticket)
        })
      })
      .catch((error) => {
        loadingIndicator.classList.add("d-none")
        showToast("Error", "No se pudieron cargar los tickets: " + error.message, "error")
      })
  }

  // Función para añadir un ticket a la tabla
  function addTicketToTable(ticket) {
    const row = document.createElement("tr")
    row.id = `ticket-row-${ticket.id_ticket}`

    row.innerHTML = `
            <td>${ticket.id_ticket}</td>
            <td>${ticket.title || "-"}</td>
            <td>${ticket.name || "-"}</td>
            <td>${ticket.license || "-"}</td>
            <td>${ticket.assigned_place || "-"}</td>
            <td>${ticket.enter_date || "-"}</td>
            <td>${ticket.enter_hour || "-"}</td>
            <td>
                <button class="btn btn-sm btn-info btn-action view-ticket" data-id="${ticket.id_ticket}">
                    <i class="bi bi-eye"></i>
                </button>
                <button class="btn btn-sm btn-primary btn-action edit-ticket" data-id="${ticket.id_ticket}">
                    <i class="bi bi-pencil"></i>
                </button>
                <button class="btn btn-sm btn-danger btn-action delete-ticket" data-id="${ticket.id_ticket}">
                    <i class="bi bi-trash"></i>
                </button>
            </td>
        `

    ticketTableBody.appendChild(row)

    // Añadir event listeners a los botones
    row.querySelector(".view-ticket").addEventListener("click", () => viewTicket(ticket.id_ticket))
    row.querySelector(".edit-ticket").addEventListener("click", () => editTicket(ticket.id_ticket))
    row.querySelector(".delete-ticket").addEventListener("click", () => confirmDelete(ticket.id_ticket))
  }

  // Función para ver detalles de un ticket
  function viewTicket(id) {
    fetch(`/api/ticket/${id}`)
      .then((response) => {
        if (!response.ok) {
          throw new Error("Ticket no encontrado")
        }
        return response.json()
      })
      .then((ticket) => {
        document.getElementById("view-id").textContent = ticket.id_ticket
        document.getElementById("view-title").textContent = ticket.title || "-"
        document.getElementById("view-name").textContent = ticket.name || "-"
        document.getElementById("view-address").textContent = ticket.address || "-"
        document.getElementById("view-telephone").textContent = ticket.telephone || "-"
        document.getElementById("view-license").textContent = ticket.license || "-"
        document.getElementById("view-assigned_place").textContent = ticket.assigned_place || "-"
        document.getElementById("view-enter_date").textContent = ticket.enter_date || "-"
        document.getElementById("view-enter_hour").textContent = ticket.enter_hour || "-"
        document.getElementById("view-message").textContent = ticket.message || "-"

        viewTicketModal.show()
      })
      .catch((error) => {
        showToast("Error", error.message, "error")
      })
  }

  // Función para editar un ticket
  function editTicket(id) {
    fetch(`/api/ticket/${id}`)
      .then((response) => {
        if (!response.ok) {
          throw new Error("Ticket no encontrado")
        }
        return response.json()
      })
      .then((ticket) => {
        document.getElementById("ticketId").value = ticket.id_ticket
        document.getElementById("title").value = ticket.title || ""
        document.getElementById("name").value = ticket.name || ""
        document.getElementById("address").value = ticket.address || ""
        document.getElementById("telephone").value = ticket.telephone || ""
        document.getElementById("license").value = ticket.license || ""
        document.getElementById("assigned_place").value = ticket.assigned_place || ""
        document.getElementById("enter_date").value = ticket.enter_date || ""
        document.getElementById("enter_hour").value = ticket.enter_hour || ""
        document.getElementById("message").value = ticket.message || ""

        document.getElementById("ticketModalLabel").textContent = "Editar Ticket"
        ticketModal.show()
      })
      .catch((error) => {
        showToast("Error", error.message, "error")
      })
  }

  // Función para confirmar eliminación
  function confirmDelete(id) {
    ticketToDelete = id
    deleteConfirmModal.show()
  }

  // Función para eliminar un ticket
  function deleteTicket() {
    if (!ticketToDelete) return

    fetch(`/api/ticket/${ticketToDelete}`, {
      method: "DELETE",
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("No se pudo eliminar el ticket")
        }

        const row = document.getElementById(`ticket-row-${ticketToDelete}`)
        if (row) {
          row.remove()
        }

        // Verificar si la tabla está vacía
        if (ticketTableBody.children.length === 0) {
          noTicketsMessage.classList.remove("d-none")
        }

        showToast("Éxito", "Ticket eliminado correctamente", "success")
        deleteConfirmModal.hide()
        ticketToDelete = null
      })
      .catch((error) => {
        showToast("Error", error.message, "error")
        deleteConfirmModal.hide()
      })
  }

  // Función para guardar un ticket (crear o actualizar)
  function saveTicket() {
    if (!ticketForm.checkValidity()) {
      ticketForm.reportValidity()
      return
    }

    const ticketId = document.getElementById("ticketId").value
    const isNewTicket = !ticketId

    const ticketData = {
      title: document.getElementById("title").value,
      name: document.getElementById("name").value,
      address: document.getElementById("address").value,
      telephone: document.getElementById("telephone").value,
      license: document.getElementById("license").value,
      assigned_place: document.getElementById("assigned_place").value,
      enter_date: document.getElementById("enter_date").value,
      enter_hour: document.getElementById("enter_hour").value,
      message: document.getElementById("message").value,
    }

    const url = isNewTicket ? "/api/ticket" : `/api/ticket/${ticketId}`
    const method = isNewTicket ? "POST" : "PUT"

    fetch(url, {
      method: method,
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(ticketData),
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("Error al guardar el ticket")
        }
        return response.json()
      })
      .then((savedTicket) => {
        ticketModal.hide()

        if (isNewTicket) {
          addTicketToTable(savedTicket)
          noTicketsMessage.classList.add("d-none")
          showToast("Éxito", "Ticket creado correctamente", "success")
        } else {
          // Actualizar la fila existente
          const row = document.getElementById(`ticket-row-${ticketId}`)
          if (row) {
            row.innerHTML = `
                            <td>${savedTicket.id_ticket}</td>
                            <td>${savedTicket.title || "-"}</td>
                            <td>${savedTicket.name || "-"}</td>
                            <td>${savedTicket.license || "-"}</td>
                            <td>${savedTicket.assigned_place || "-"}</td>
                            <td>${savedTicket.enter_date || "-"}</td>
                            <td>${savedTicket.enter_hour || "-"}</td>
                            <td>
                                <button class="btn btn-sm btn-info btn-action view-ticket" data-id="${savedTicket.id_ticket}">
                                    <i class="bi bi-eye"></i>
                                </button>
                                <button class="btn btn-sm btn-primary btn-action edit-ticket" data-id="${savedTicket.id_ticket}">
                                    <i class="bi bi-pencil"></i>
                                </button>
                                <button class="btn btn-sm btn-danger btn-action delete-ticket" data-id="${savedTicket.id_ticket}">
                                    <i class="bi bi-trash"></i>
                                </button>
                            </td>
                        `

            // Añadir event listeners a los botones
            row.querySelector(".view-ticket").addEventListener("click", () => viewTicket(savedTicket.id_ticket))
            row.querySelector(".edit-ticket").addEventListener("click", () => editTicket(savedTicket.id_ticket))
            row.querySelector(".delete-ticket").addEventListener("click", () => confirmDelete(savedTicket.id_ticket))

            // Añadir efecto de resaltado
            row.classList.add("highlight-row")
          }

          showToast("Éxito", "Ticket actualizado correctamente", "success")
        }
      })
      .catch((error) => {
        showToast("Error", error.message, "error")
      })
  }

  // Función para resetear el formulario
  function resetForm() {
    ticketForm.reset()
    document.getElementById("ticketId").value = ""
    document.getElementById("ticketModalLabel").textContent = "Nuevo Ticket"

    // Configurar fecha y hora actuales
    document.getElementById("enter_date").valueAsDate = new Date()
    const now = new Date()
    document.getElementById("enter_hour").value =
      `${String(now.getHours()).padStart(2, "0")}:${String(now.getMinutes()).padStart(2, "0")}`
  }

  // Función para mostrar notificaciones toast
  function showToast(title, message, type = "info") {
    toastTitle.textContent = title
    toastMessage.textContent = message

    // Eliminar clases anteriores
    toastElement.classList.remove("bg-success", "bg-danger", "bg-info", "text-white")

    // Añadir clase según el tipo
    if (type === "success") {
      toastElement.classList.add("bg-success", "text-white")
    } else if (type === "error") {
      toastElement.classList.add("bg-danger", "text-white")
    } else {
      toastElement.classList.add("bg-info", "text-white")
    }

    toast.show()
  }
})
