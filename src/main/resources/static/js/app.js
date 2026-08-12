const API_URL = '/contenido';

// Cargar datos al iniciar la pantalla
document.addEventListener('DOMContentLoaded', () => {
    cargarTabla();
});

// 1. GET: Consultar los registros guardados
async function cargarTabla(query = '') {
    try {
        const url = query ? `${API_URL}?query=${encodeURIComponent(query)}` : API_URL;
        const response = await fetch(url);
        const datos = await response.json();
        renderizarTabla(datos);
    } catch (error) {
        console.error('Error al cargar la tabla:', error);
    }
}

// Renderizar las filas recibidas de Supabase
function renderizarTabla(lista) {
    const tbody = document.getElementById('tablaCuerpo');
    if (!tbody) return;
    
    tbody.innerHTML = '';

    if (!lista || lista.length === 0) {
        tbody.innerHTML = '<tr><td colspan="5" style="text-align:center; color:#94a3b8;">No se encontraron registros.</td></tr>';
        return;
    }

    lista.forEach(item => {
        const tr = document.createElement('tr');
        
        // Adaptación flexible según la estructura recibida
        const titulo = item.titulo || item.contenido?.titulo || 'Sin título';
        const texto = item.texto || item.contenido?.texto || 'Sin texto';
        const categoria = item.categoria || item.categoriaEntity?.descripcion || 'Sin categoría';
        const probabilidad = item.probabilidad ? `${(item.probabilidad * 100).toFixed(1)}%` : 'N/A';
        
        const palabrasClave = item.informacion_adicional || item.informacionAdicional || [];
        const tagsHtml = Array.isArray(palabrasClave) 
            ? palabrasClave.map(p => `<span class="tag">${escapeHtml(p)}</span>`).join(' ')
            : '';

        tr.innerHTML = `
            <td><strong>${escapeHtml(titulo)}</strong></td>
            <td style="max-width: 250px; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">${escapeHtml(texto)}</td>
            <td><span class="badge">${escapeHtml(categoria)}</span></td>
            <td>${probabilidad}</td>
            <td><div class="keywords">${tagsHtml}</div></td>
        `;
        tbody.appendChild(tr);
    });
}

// 2. Buscador en tiempo real
function filtrarTabla() {
    const query = document.getElementById('searchInput').value;
    cargarTabla(query);
}

// 3. POST: Enviar texto del Pop-up a Spring Boot
async function guardarTexto() {
    const tituloInput = document.getElementById('tituloInput');
    const textoInput = document.getElementById('textoInput');
    const btnConfirmar = document.getElementById('btnConfirmar');

    const titulo = tituloInput ? tituloInput.value.trim() : '';
    const texto = textoInput ? textoInput.value.trim() : '';

    if (!titulo || !texto) {
        alert('Por favor complete tanto el título como el texto.');
        return;
    }

    try {
        btnConfirmar.innerText = "Procesando...";
        btnConfirmar.disabled = true;

        const response = await fetch(API_URL, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ titulo: titulo, texto: texto })
        });

        if (response.ok) {
            cerrarModal();
            cargarTabla(); // Recarga la tabla en vivo con la nueva clasificación
        } else {
            const errData = await response.json();
            alert('Error: ' + (errData.message || JSON.stringify(errData)));
        }
    } catch (error) {
        console.error('Error al guardar:', error);
        alert('Ocurrió un error al conectar con el servidor.');
    } finally {
        btnConfirmar.innerText = "Confirmar";
        btnConfirmar.disabled = false;
    }
}

// Lector de archivos .txt para el Pop-up
function leerArchivoTxt(event) {
    const file = event.target.files[0];
    if (!file) return;

    const reader = new FileReader();
    reader.onload = function(e) {
        document.getElementById('textoInput').value = e.target.result;
        const tituloInput = document.getElementById('tituloInput');
        if (tituloInput && !tituloInput.value) {
            // Usa el nombre del archivo como título por defecto
            tituloInput.value = file.name.replace('.txt', '');
        }
    };
    reader.readAsText(file);
}

// Manejo de la ventana Modal
function abrirModal() {
    const modal = document.getElementById('modalOverlay');
    if (modal) {
        modal.style.display = 'flex';
    }
}

function cerrarModal() {
    const modal = document.getElementById('modalOverlay');
    if (modal) {
        modal.style.display = 'none';
    }
    const tituloInput = document.getElementById('tituloInput');
    const textoInput = document.getElementById('textoInput');
    const fileInput = document.getElementById('fileInput');

    if (tituloInput) tituloInput.value = '';
    if (textoInput) textoInput.value = '';
    if (fileInput) fileInput.value = '';
}

function escapeHtml(str) {
    return String(str)
        .replace(/&/g, '&amp;')
        .replace(/</g, '&lt;')
        .replace(/>/g, '&gt;')
        .replace(/"/g, '&quot;')
        .replace(/'/g, '&#039;');
}
