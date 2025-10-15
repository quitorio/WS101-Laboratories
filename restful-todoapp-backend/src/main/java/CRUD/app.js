const API_BASE = '/users/darshan/todos';

const $ = id => document.getElementById(id);
const tbody = $('todos-body');
const form = $('todo-form');
const saveBtn = $('save-btn');
const cancelBtn = $('cancel-btn');

async function listTodos() {
  tbody.innerHTML = '<tr><td colspan="5">Loading...</td></tr>';
  try {
    const res = await fetch(API_BASE);
    if (!res.ok) throw new Error('Failed to fetch todos');
    const todos = await res.json();
    renderTodos(todos || []);
  } catch (e) {
    tbody.innerHTML = `<tr><td colspan="5">Error: ${e.message}</td></tr>`;
  }
}

function renderTodos(todos){
  if (!todos.length) { tbody.innerHTML = '<tr><td colspan="5">No todos</td></tr>'; return }
  tbody.innerHTML = '';
  todos.forEach(todo => {
    const tr = document.createElement('tr');
    tr.className = 'todo-item';
    tr.innerHTML = `
      <td colspan="5">
        <div class="todo-row" data-id="${todo.id}">
          <div class="col-id">${todo.id ?? ''}</div>
          <div class="col-desc">${escapeHtml(todo.description)}</div>
          <div class="col-target">${todo.targetDate ?? ''}</div>
          <div class="col-done">${todo.done ? '✔' : ''}</div>
          <div class="col-actions">
            <button class="action-btn" data-action="edit" data-id="${todo.id}">Edit</button>
            <button class="action-btn delete" data-action="delete" data-id="${todo.id}">Delete</button>
          </div>
        </div>
      </td>
    `;
    tbody.appendChild(tr);
  })
}

function escapeHtml(s){ if (!s) return ''; return s.replace(/[&<>"']/g, c => ({'&':'&amp;','<':'&lt;','>':'&gt;','"':'&quot;','\'':'&#39;'})[c]) }

form.addEventListener('submit', async (e)=>{
  e.preventDefault();
  const id = $('todo-id').value;
  const description = $('description').value.trim();
  const targetDate = $('targetDate').value || null;
  const done = $('done').checked;

  const payload = { description, targetDate, done };

  try {
    if (id) {
      const res = await fetch(`${API_BASE}/${id}`, { method:'PUT', headers:{'Content-Type':'application/json'}, body: JSON.stringify(payload) });
      if (!res.ok) throw new Error('Update failed');
    } else {
      const res = await fetch(API_BASE, { method:'POST', headers:{'Content-Type':'application/json'}, body: JSON.stringify(payload) });
      if (!res.ok) throw new Error('Create failed');
    }
    clearForm();
    listTodos();
  } catch (err) {
    alert(err.message);
  }
});

tbody.addEventListener('click', async (e)=>{
  const btn = e.target.closest('button');
  if (!btn) return;
  const id = btn.dataset.id;
  const action = btn.dataset.action;
  if (action === 'delete'){
    if (!confirm('Delete this todo?')) return;
    const res = await fetch(`${API_BASE}/${id}`, { method: 'DELETE' });
    if (res.ok) listTodos(); else alert('Delete failed');
  } else if (action === 'edit'){
    const res = await fetch(`${API_BASE}/${id}`);
    if (!res.ok) { alert('Failed to load'); return }
    const todo = await res.json();
    $('todo-id').value = todo.id;
    $('description').value = todo.description || '';
    $('targetDate').value = todo.targetDate || '';
    $('done').checked = !!todo.done;
    saveBtn.textContent = 'Update';
  }
});

$('refresh').addEventListener('click', () => listTodos());
$('clear-local').addEventListener('click', clearForm);
cancelBtn.addEventListener('click', clearForm);

function clearForm(){
  $('todo-id').value='';
  $('description').value='';
  $('targetDate').value='';
  $('done').checked=false;
  saveBtn.textContent = 'Add';
}

listTodos();
