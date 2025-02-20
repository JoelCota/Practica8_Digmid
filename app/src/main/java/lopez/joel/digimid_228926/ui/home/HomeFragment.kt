package lopez.joel.digimid_228926.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.TextView
import androidx.fragment.app.Fragment
import lopez.joel.digimid_228926.R
import lopez.joel.digimid_228926.Task
import lopez.joel.digimid_228926.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    // Lista de tareas que se cargarán
    private val taskList: MutableList<Task> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        // Llenar las tareas estáticas al inicio
        loadTasks()

        val adapter = TaskAdapter(requireContext(), taskList) // Adaptador con la lista de tareas
        binding.reminders.adapter = adapter

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // Función que carga las 10 tareas estáticas
    private fun loadTasks() {
        taskList.add(Task("Tarea 1", "Lun, Mar, Mié", "10:00 AM"))
        taskList.add(Task("Tarea 2", "Jue, Vie", "11:00 AM"))
        taskList.add(Task("Tarea 3", "Lun, Mié, Vie", "12:00 PM"))
        taskList.add(Task("Tarea 4", "Mar, Jue, Sab", "1:00 PM"))
        taskList.add(Task("Tarea 5", "Dom, Lun", "2:00 PM"))
        taskList.add(Task("Tarea 6", "Mar, Mié, Jue", "3:00 PM"))
        taskList.add(Task("Tarea 7", "Vie, Sab", "4:00 PM"))
        taskList.add(Task("Tarea 8", "Lun, Mar", "5:00 PM"))
        taskList.add(Task("Tarea 9", "Jue, Vie, Sab", "6:00 PM"))
        taskList.add(Task("Tarea 10", "Dom, Mié", "7:00 PM"))
    }

    // Adaptador para mostrar las tareas en el GridView
    class TaskAdapter(private val context: Context, private val taskList: List<Task>) : BaseAdapter() {

        override fun getCount(): Int = taskList.size

        override fun getItem(position: Int): Any = taskList[position]

        override fun getItemId(position: Int): Long = position.toLong()

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_task, parent, false)
            val task = taskList[position]

            val nameTextView = view.findViewById<TextView>(R.id.taskName)
            val daysTextView = view.findViewById<TextView>(R.id.taskDays)
            val timeTextView = view.findViewById<TextView>(R.id.time)

            nameTextView.text = task.name
            daysTextView.text = task.days
            timeTextView.text = task.time

            return view
        }
    }
}
